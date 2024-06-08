package com.alkewallet.wallet.service;

import com.alkewallet.wallet.model.User;
import com.alkewallet.wallet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    public void postUser(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        userRepository.save(user);
    }
}
