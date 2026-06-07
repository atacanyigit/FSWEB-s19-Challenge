package com.example.demo.controller;

import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService1;

    @Autowired
    public UserController(UserService userService1){

        this.userService1 = userService1;
    }

    @GetMapping
    public List<User> findAll(){
        return userService1.findAll();
    }

    @GetMapping("/findById")
    public User findById(@PathVariable Long id){
        return userService1.findById(id);
    }

    @PostMapping
    public User save(@RequestBody User user){
        return userService1.save(user);
    }

    @PutMapping("/{id}")
    public User update (@PathVariable Long id, @RequestBody User user){
        return userService1.update(id, user);
    }


    @DeleteMapping("/{id}")
    public User delete (@PathVariable Long id){
        return userService1.delete(id);
    }
}
