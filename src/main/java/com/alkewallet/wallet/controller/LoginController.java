package com.alkewallet.wallet.controller;

import com.alkewallet.wallet.model.User;
import com.alkewallet.wallet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("content", "login");
        return "index";
    }

    @PostMapping("/login")
    public String login(User user) {
        if (userService.authenticate(user.getEmail(), user.getPassword())) {
            return "redirect:/account";
        } else {
            return "redirect:/login?error";
        }
    }
}
