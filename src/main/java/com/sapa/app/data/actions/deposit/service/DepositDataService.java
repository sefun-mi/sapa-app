package com.sapa.app.data.actions.deposit.service;


import com.sapa.app.data.domain.entry.model.Entry;
import com.sapa.app.data.domain.entry.repository.EntryRepository;
import com.sapa.app.data.domain.glaccount.model.GLAccount;
import com.sapa.app.data.domain.glaccount.repository.GLAccountRepository;
import com.sapa.app.data.domain.transaction.enums.TransactionStatus;
import com.sapa.app.data.domain.transaction.model.Transaction;
import com.sapa.app.data.domain.transaction.repository.TransactionRepository;
import com.sapa.app.data.domain.useraccount.model.UserAccount;
import com.sapa.app.data.domain.useraccount.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepositDataService {
    private final EntryRepository entryRepository;
    private final TransactionRepository transactionRepository;
    private final UserAccountRepository userAccountRepository;
    private final GLAccountRepository glAccountRepository;

    public void deposit(String amountInMinor, String username){
        BigDecimal amountInBigDecimal = new BigDecimal(amountInMinor);
        UserAccount userAccount = getUserAccount(username);
        GLAccount depositsGLAccount = getGLAccount();

        Transaction transaction = new Transaction();
        transaction.setEntries(new ArrayList<>());

        Entry debitEntry = new Entry();
        debitEntry.setDebit(amountInBigDecimal);
        transaction.getEntries().add(debitEntry);
        List<Entry> depositsAccountEntries = depositsGLAccount.getEntries() != null ? depositsGLAccount.getEntries() : new ArrayList<>();
        depositsAccountEntries.add(debitEntry);
        depositsGLAccount.setEntries(depositsAccountEntries);
        glAccountRepository.save(depositsGLAccount);

        Entry creditEntry = new Entry();
        creditEntry.setDebit(amountInBigDecimal);
        transaction.getEntries().add(creditEntry);
        List<Entry> userAccountEntries = userAccount.getEntries() != null ? userAccount.getEntries() : new ArrayList<>();
        userAccountEntries.add(creditEntry);
        userAccount.setEntries(userAccountEntries);
        userAccountRepository.save(userAccount);

        transaction.setStatus(TransactionStatus.POSTED);
        transactionRepository.save(transaction);
    }

    private UserAccount getUserAccount(String username){
        return userAccountRepository.findByWalletUserUsername(username).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "User account not found"));
    }

    private GLAccount getGLAccount(){
        Optional<GLAccount> optional = glAccountRepository.findByName("DEPOSITS");
        if(optional.isPresent()){
            return optional.get();
        }

        GLAccount newDepositsGlAccount = new GLAccount();
        newDepositsGlAccount.setName("DEPOSITS");
        return newDepositsGlAccount;
    }

}