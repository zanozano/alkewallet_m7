package com.alkewallet.wallet.controller;

import com.alkewallet.wallet.model.Account;
import com.alkewallet.wallet.model.Transaction;
import com.alkewallet.wallet.model.User;
import com.alkewallet.wallet.service.AccountService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/account")
    public String showAccountPage(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        List<Account> accounts = accountService.getUserAccounts(user.getEmail());
        model.addAttribute("accounts", accounts);
        return "accountView";
    }
}
