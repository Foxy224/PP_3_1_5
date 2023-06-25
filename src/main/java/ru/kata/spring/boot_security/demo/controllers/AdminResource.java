package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.service.UsersServiceImpl;

@RestController
public class AdminResource {

    private final UsersServiceImpl usersService;
    private final RoleServiceImpl roleService;

    @Autowired
    public AdminResource(UsersServiceImpl usersService, RoleServiceImpl roleService) {
        this.usersService = usersService;
        this.roleService = roleService;
    }

    @GetMapping("/api/v1/user/{userId}")
    public User findById(@PathVariable long userId) {
        return usersService.showUser(userId);
    }

    @PostMapping("/api/v1/user")
    public User create(@RequestBody User user) {
        return usersService.save(user);
    }
}