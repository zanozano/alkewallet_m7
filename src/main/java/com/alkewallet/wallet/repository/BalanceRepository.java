package com.alkewallet.wallet.repository;

import com.alkewallet.wallet.model.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BalanceRepository extends JpaRepository<Balance, UUID> {
    Balance findByAccountIdAndCurrencyCode(UUID accountId, String currencyCode);
}