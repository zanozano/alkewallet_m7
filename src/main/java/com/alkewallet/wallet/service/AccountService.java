package com.alkewallet.wallet.service;

import com.alkewallet.wallet.model.Account;
import com.alkewallet.wallet.model.User;
import com.alkewallet.wallet.repository.AccountRepository;
import com.alkewallet.wallet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository, UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    public List<Account> getUserAccounts(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            return accountRepository.findByUserId(user.getId());
        }
        return Collections.emptyList();
    }
}
