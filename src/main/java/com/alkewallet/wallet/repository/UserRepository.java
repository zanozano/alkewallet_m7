package com.alkewallet.wallet.repository;

import com.alkewallet.wallet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    User findByEmail(String email);
    List<User> findByIdIn(List<UUID> ids);
}

