package com.alkewallet.wallet.controller;

import com.alkewallet.wallet.model.User;
import com.alkewallet.wallet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/create")
    public String createUser(User user, Model model) {
        User existingUser = userService.getUserByEmail(user.getEmail());

        if (existingUser != null) {
            return "redirect:/login";
        } else {
            userService.postUser(user.getEmail(), user.getPassword());
            return "redirect:/account";
        }
    }
}
