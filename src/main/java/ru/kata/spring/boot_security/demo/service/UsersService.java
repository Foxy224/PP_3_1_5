package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.entity.Users;
import ru.kata.spring.boot_security.demo.entity.Users;

import java.util.List;

public interface UsersService {
    void add(Users user);
    void delete(long id);
    List<Users> read();
    void update(Users user);
    Users showUser(long id);
    Users findByUsername(String username);
}
