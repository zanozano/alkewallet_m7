package com.alkewallet.wallet.repository;

import com.alkewallet.wallet.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    List<Transaction> findBySenderIdOrReceiverId(UUID senderId, UUID receiverId);
}
