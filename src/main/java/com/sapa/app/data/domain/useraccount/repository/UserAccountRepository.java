package com.sapa.app.data.domain.useraccount.repository;


import com.sapa.app.data.domain.useraccount.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    Optional<UserAccount> findByWalletUserUsername(String username);
}
