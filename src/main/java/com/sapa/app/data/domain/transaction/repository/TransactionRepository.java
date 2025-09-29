package com.sapa.app.data.domain.transaction.repository;


import com.sapa.app.data.domain.transaction.model.Transaction;
import com.sapa.app.data.domain.useraccount.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
