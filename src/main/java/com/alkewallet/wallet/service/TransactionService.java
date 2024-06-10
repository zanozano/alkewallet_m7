package com.alkewallet.wallet.service;

import com.alkewallet.wallet.dto.TransactionRequest;
import com.alkewallet.wallet.model.Transaction;
import com.alkewallet.wallet.model.User;
import com.alkewallet.wallet.repository.TransactionRepository;
import com.alkewallet.wallet.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final HttpSession session;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository,
                              UserRepository userRepository,
                              HttpSession session) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
        this.session = session;
    }

    public List<Transaction> getUserTransactions(String userEmail) {
        User user = userRepository.findByEmail(userEmail);
        UUID userId = user.getId();
        return transactionRepository.findBySenderIdOrReceiverId(userId, userId);
    }

    public void deposit(TransactionRequest request) {
        if (request.getAmount() <= 0) {
            return;
        }

        User user = (User) session.getAttribute("user");
        if (user == null) {
            return;
        }

        String userEmail = user.getEmail();

        User userEntity = userRepository.findByEmail(userEmail);
        if (userEntity == null) {
            return;
        }

        Transaction transaction = new Transaction();
        transaction.setTransactionId(UUID.randomUUID());
        transaction.setAmount(request.getAmount());
        transaction.setCurrencyCode(request.getCurrencyCode());
        transaction.setTransactionDate(new Date());
        transaction.setSenderId(userEntity.getId());
        transaction.setReceiverId(userEntity.getId());
        transaction.setType("DEPOSIT");

        try {
            transactionRepository.save(transaction);
        } catch (Exception ignored) {
        }
    }
}
