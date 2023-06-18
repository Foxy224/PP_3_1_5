package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.entity.Users;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.util.List;

@Service
public class UserServiceImp implements UsersService {
    private final UserRepository userRepository;
    @Autowired
    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void add(Users user) {
        userRepository.save(user);
    }

    @Transactional
    public void delete(long id) {
        userRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Users> read() {
        return userRepository.findAll();
    }

    @Transactional
    public void update(Users user) {
        userRepository.save(user);
    }
    @Transactional
    public Users showUser(long id) {
        return userRepository.getOne(id);
    }
    public Users findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
