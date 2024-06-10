package com.alkewallet.wallet.service;

import com.alkewallet.wallet.model.User;
import com.alkewallet.wallet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final HttpSession session;

    @Autowired
    public UserService(UserRepository userRepository, HttpSession session) {
        this.userRepository = userRepository;
        this.session = session;
    }

    // user session
    public boolean authenticate(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            session.setAttribute("user", user);
            return true;
        }
        return false;
    }

    // get user
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // create user
    public void postUser(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        userRepository.save(user);
    }

    public Map<UUID, String> getEmailsForUserIds(List<UUID> userIds) {
        List<User> users = userRepository.findByIdIn(userIds);
        return users.stream()
                .collect(Collectors.toMap(User::getId, User::getEmail));
    }
}
