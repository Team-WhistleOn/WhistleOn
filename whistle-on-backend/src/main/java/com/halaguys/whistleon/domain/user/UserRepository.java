package com.halaguys.whistleon.domain.user;

import com.sun.istack.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findUserByEmail(String email);
    Optional<User> findUserByUserId(int userId);
}
