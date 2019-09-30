package com.quangchinh.demo.service;

import com.quangchinh.demo.dao.User;

import java.util.List;

public interface UserService {

    User create(User user);

    List<User> getAll();

    User getById(String id);

    User updateUser(User user);

    boolean deleteUser(String id);
}
