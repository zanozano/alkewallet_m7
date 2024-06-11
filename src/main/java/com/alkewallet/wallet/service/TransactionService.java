package com.alkewallet.wallet.service;

import com.alkewallet.wallet.dto.TransactionRequest;
import com.alkewallet.wallet.model.Account;
import com.alkewallet.wallet.model.Transaction;
import com.alkewallet.wallet.model.User;
import com.alkewallet.wallet.model.Balance;
import com.alkewallet.wallet.repository.AccountRepository;
import com.alkewallet.wallet.repository.TransactionRepository;
import com.alkewallet.wallet.repository.UserRepository;
import com.alkewallet.wallet.repository.BalanceRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final BalanceRepository balanceRepository;
    private final HttpSession session;

    @Autowired
    public TransactionService(AccountRepository accountReposiroty,
                              TransactionRepository transactionRepository,
                              UserRepository userRepository,
                              BalanceRepository balanceRepository,
                              HttpSession session) {
        this.accountRepository = accountReposiroty;
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
        this.balanceRepository = balanceRepository;
        this.session = session;
    }

    // get transaction list
    public List<Transaction> getUserTransactions(String userEmail) {
        User user = userRepository.findByEmail(userEmail);
        UUID userId = user.getId();
        return transactionRepository.findBySenderIdOrReceiverId(userId, userId);
    }

    // post transaction deposit
    @Transactional
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
            updateBalance(userEntity.getId(), request.getCurrencyCode(), request.getAmount(), "DEPOSIT");
        } catch (Exception e) {
            System.out.println("Error depositing funds: " + e.getMessage());
        }
    }

    // post transaction withdraw
    @Transactional
    public void withdraw(TransactionRequest request) {
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
        transaction.setAmount(-request.getAmount());
        transaction.setCurrencyCode(request.getCurrencyCode());
        transaction.setTransactionDate(new Date());
        transaction.setSenderId(userEntity.getId());
        transaction.setReceiverId(userEntity.getId());
        transaction.setType("WITHDRAWAL");

        try {
            transactionRepository.save(transaction);
            updateBalance(userEntity.getId(), request.getCurrencyCode(), request.getAmount(), "WITHDRAWAL");
        } catch (Exception e) {
            System.out.println("Error depositing funds: " + e.getMessage());
        }
    }

    //update balance
    private void updateBalance(UUID userId, String currencyCode, double amount, String transactionType) {
        Account accountId = accountRepository.findAccountByUserIdAndCurrencyCode(userId,currencyCode);

        if (accountId != null) {
            Balance balance = balanceRepository.findByAccountIdAndCurrencyCode(accountId.getId(), currencyCode);

            if (balance != null) {
                if ("DEPOSIT".equals(transactionType)) {
                    balance.setAmount(balance.getAmount() + amount);
                } else if ("WITHDRAWAL".equals(transactionType)) {
                    balance.setAmount(balance.getAmount() - amount);
                } else {
                    System.out.println("Unknown transaction type: " + transactionType);
                    return;
                }
                balanceRepository.save(balance);
            } else {
                System.out.println("Balance not found for account: " + accountId + ", currency: " + currencyCode);
            }
        } else {
            System.out.println("Account not found for user: " + userId);
        }
    }



}
