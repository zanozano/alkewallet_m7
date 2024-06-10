package com.alkewallet.wallet.service;

import com.alkewallet.wallet.model.Account;
import com.alkewallet.wallet.model.Balance;
import com.alkewallet.wallet.model.User;
import com.alkewallet.wallet.repository.AccountRepository;
import com.alkewallet.wallet.repository.BalanceRepository;
import com.alkewallet.wallet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final BalanceRepository balanceRepository;
    private final UserRepository userRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository, BalanceRepository balanceRepository, UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.balanceRepository = balanceRepository;
        this.userRepository = userRepository;
    }

    public List<Account> getUserAccounts(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            List<Account> accounts = accountRepository.findByUserId(user.getId());
            accounts.forEach(account -> {
                List<Balance> balances = balanceRepository.findByAccountId(account.getId());
                account.setBalances(balances);
            });
            return accounts;
        }
        return Collections.emptyList();
    }
}
