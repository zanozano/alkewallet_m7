package com.alkewallet.wallet.service;

import com.alkewallet.wallet.model.Account;
import com.alkewallet.wallet.model.Balance;
import com.alkewallet.wallet.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ExchangeService {

    private final AccountRepository accountRepository;

    @Autowired
    public ExchangeService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void exchangeCurrency(UUID userId, String targetCurrencyCode, double amountInCLP) {

        double exchangeRate = getExchangeRate(targetCurrencyCode);

        double targetAmount = amountInCLP / exchangeRate;

        Account clpAccount = accountRepository.findAccountByUserIdAndCurrencyCode(userId, "CLP");
        Account targetAccount = accountRepository.findAccountByUserIdAndCurrencyCode(userId, targetCurrencyCode);

        if (clpAccount == null || targetAccount == null) {
            throw new IllegalArgumentException("Account not found for the given currency code.");
        }

        updateBalances(clpAccount, targetAccount, amountInCLP, targetAmount);
    }

    private double getExchangeRate(String currencyCode) {
        return switch (currencyCode) {
            case "USD" -> 800.0;
            case "EUR" -> 900.0;
            case "THB" -> 25.0;
            case "CNY" -> 125.0;
            default -> throw new IllegalArgumentException("Unsupported currency code.");
        };
    }

    //update balance by exchange
    private void updateBalances(Account clpAccount, Account targetAccount, double amountInCLP, double targetAmount) {
        Balance clpBalance = clpAccount.getBalances().stream().filter(b -> b.getCurrencyCode().equals("CLP")).findFirst().orElse(null);
        Balance targetBalance = targetAccount.getBalances().stream().filter(b -> b.getCurrencyCode().equals(targetAccount.getCurrencyCode())).findFirst().orElse(null);

        if (clpBalance == null || targetBalance == null) {
            throw new IllegalArgumentException("Balance not found for the given currency code.");
        }

        if (clpBalance.getAmount() < amountInCLP) {
            throw new IllegalArgumentException("Insufficient funds in CLP account.");
        }

        clpBalance.setAmount(clpBalance.getAmount() - amountInCLP);
        targetBalance.setAmount(targetBalance.getAmount() + targetAmount);

        accountRepository.save(clpAccount);
        accountRepository.save(targetAccount);
    }
}
