package com.alkewallet.wallet.controller;

import com.alkewallet.wallet.dto.TransactionRequest;
import com.alkewallet.wallet.dto.TransactionResponse;
import com.alkewallet.wallet.model.Account;
import com.alkewallet.wallet.model.Balance;
import com.alkewallet.wallet.model.User;
import com.alkewallet.wallet.model.Transaction;
import com.alkewallet.wallet.service.AccountService;
import com.alkewallet.wallet.service.TransactionService;
import com.alkewallet.wallet.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Controller
public class AccountController {

    private final AccountService accountService;
    private final TransactionService transactionService;
    private final UserService userService;

    @Autowired
    public AccountController(AccountService accountService, TransactionService transactionService, UserService userService) {
        this.accountService = accountService;
        this.transactionService = transactionService;
        this.userService = userService;
    }

    @GetMapping("/account")
    public String showAccountPage(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        List<Account> accounts = accountService.getUserAccounts(user.getEmail());
        List<Balance> balances = getBalancesFromAccounts(accounts);
        List<Transaction> transactions = transactionService.getUserTransactions(user.getEmail());

        Map<UUID, String> userEmails = userService.getEmailsForUserIds(
                transactions.stream()
                        .flatMap(t -> Stream.of(t.getSenderId(), t.getReceiverId()))
                        .distinct()
                        .collect(Collectors.toList())
        );

        model.addAttribute("user", user);
        model.addAttribute("balances", balances);
        model.addAttribute("transactions", transactions);
        model.addAttribute("content", "account");
        model.addAttribute("userEmails", userEmails);

        return "index";
    }

    private List<Balance> getBalancesFromAccounts(List<Account> accounts) {
        List<Balance> balances = new ArrayList<>();
        for (Account account : accounts) {
            balances.addAll(account.getBalances());
        }
        return balances;
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @PostMapping("/deposit")
    public String processDeposit(@RequestParam("currencyCode") String currencyCode,
                                 @RequestParam("amount") double amount,
                                 RedirectAttributes redirectAttributes) {
        TransactionRequest request = new TransactionRequest();
        request.setCurrencyCode(currencyCode);
        request.setAmount(amount);

        transactionService.deposit(request);
        redirectAttributes.addFlashAttribute("message", "Deposit successful!");
        return "redirect:/account";
    }


}
