package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("No users found! : " + id));
    }

    @Override
    public List<User> findByUserId(Long userId) {
        return userRepository.findByUserId(userId);
    }

    @Override
    public User update(Long id, User user) {
        User users= userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No users found!: " + id));


        return userRepository.save(users);

    }

    @Override
    public User delete(Long id) {
        User users = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Silinecek kullanıcı bulunamadı: " + id));


        userRepository.delete(users);


        return users;

    }
}
