package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.entity.User;

import java.util.List;

public interface UsersService {
    void add(User user);
    void deleteById(long id);
    List<User> read();
    void update(User user);
    User showUser(long id);
    User findByUsername(String username);
    User save(User user);
}
