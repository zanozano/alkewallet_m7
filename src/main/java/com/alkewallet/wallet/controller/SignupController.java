package com.alkewallet.wallet.controller;

import com.alkewallet.wallet.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignupController {

    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("content", "signup");
        return "index";
    }

    @PostMapping("/signup")
    public String signup() {
        return "redirect:/home";
    }
}
