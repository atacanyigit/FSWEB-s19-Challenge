package com.example.demo.service;


import com.example.demo.entity.User;

import java.util.List;

public interface UserService {
    User save (User user);

    List<User> findAll();

    User findById(Long id);

    List<User> findByUserId(Long userId);

    User update(Long id, User user);

    User delete(Long id);
}
