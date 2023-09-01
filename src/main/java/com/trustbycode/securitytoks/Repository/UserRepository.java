package com.trustbycode.securitytoks.Repository;

import com.trustbycode.securitytoks.Client.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);
}