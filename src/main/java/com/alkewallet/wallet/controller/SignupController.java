package com.alkewallet.wallet.controller;

import com.alkewallet.wallet.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.alkewallet.wallet.service.UserService;

@Controller
@RequestMapping("/signup")
public class SignupController {

    private final UserService userService;

    @Autowired
    public SignupController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showSignupPage(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("content", "signup");
        return "index";
    }

    @PostMapping
    public String signup(@ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
        userService.postUser(user.getEmail(), user.getPassword());
        return "redirect:/login";
    }
}
