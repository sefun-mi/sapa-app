package com.sapa.app.data.domain.glaccount.repository;

import com.sapa.app.data.domain.glaccount.model.GLAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GLAccountRepository extends JpaRepository<GLAccount, Long> {
    Optional<GLAccount> findByName(String name);
}
