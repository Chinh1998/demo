package com.quangchinh.demo.repository;

import com.quangchinh.demo.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
