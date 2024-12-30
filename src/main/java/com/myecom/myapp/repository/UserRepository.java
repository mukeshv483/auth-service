package com.myecom.myapp.repository;

import com.myecom.myapp.domain.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUserName(String userName);

    Optional<User> findByEmail(String email);
}
