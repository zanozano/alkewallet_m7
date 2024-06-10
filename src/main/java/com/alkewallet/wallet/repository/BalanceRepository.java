package com.alkewallet.wallet.repository;

import com.alkewallet.wallet.model.Balance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BalanceRepository extends JpaRepository<Balance, UUID> {
    List<Balance> findByAccountId(UUID accountId);
}