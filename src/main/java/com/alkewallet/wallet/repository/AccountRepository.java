package com.alkewallet.wallet.repository;

import com.alkewallet.wallet.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {

    List<Account> findByUserId(UUID userId);

    @Query("SELECT a.id FROM Account a WHERE a.userId = :userId AND a.currencyCode = :currencyCode")
    UUID findAccountIdByUserIdAndCurrencyCode(@Param("userId") UUID userId, @Param("currencyCode") String currencyCode);
}
