package com.alkewallet.wallet.service;

import com.alkewallet.wallet.model.Transaction;
import com.alkewallet.wallet.model.User;
import com.alkewallet.wallet.repository.TransactionRepository;
import com.alkewallet.wallet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    public List<Transaction> getUserTransactions(String userEmail) {
        User user = userRepository.findByEmail(userEmail);
        UUID userId = user.getId();
        return transactionRepository.findBySenderIdOrReceiverId(userId, userId);
    }
}
