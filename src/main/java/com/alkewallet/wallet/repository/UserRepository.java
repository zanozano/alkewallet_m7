package com.alkewallet.wallet.repository;

import com.alkewallet.wallet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    User findByEmailAndPassword(String email, String password);
}
