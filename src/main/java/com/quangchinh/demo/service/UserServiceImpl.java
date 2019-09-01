package com.quangchinh.demo.service;

import com.quangchinh.demo.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private List<User> listUser = new ArrayList<>();

    @Override
    public User create(User user) {
        listUser.add(user);
        return user;
    }

    @Override
    public List<User> getAll() {
        return listUser;
    }

    @Override
    public User getById(String id) {
        for (User user : listUser) {
            if (user.getId().equals(id))
                return user;
        }
        return null;
    }

    @Override
    public User updateUser(User user) {
        for (User user1 : listUser) {
            if (user1.getId().equals(user.getId())) {
                user1.setName(user.getName());
                user1.setAddress(user.getAddress());
            }
            return user1;
        }
        return null;
    }

    @Override
    public boolean deleteUser(String id) {
        listUser.removeIf(user -> user.getId().equals(id));
        return true;
    }
}
