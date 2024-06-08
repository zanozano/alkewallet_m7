package com.alkewallet.wallet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountController {

    @GetMapping("/account")
    public String showHomePage(Model model) {
        model.addAttribute("content", "account");
        return "index";
    }
}
