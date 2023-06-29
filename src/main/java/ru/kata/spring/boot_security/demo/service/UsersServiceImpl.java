package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.repositories.UsersRepository;

import java.util.List;
import java.util.Set;

@Service
public class UsersServiceImpl implements UsersService {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsersServiceImpl(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void add(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        usersRepository.save(user);
    }

    @Transactional
    public void delete(long id) {
        usersRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<User> read() {
        return usersRepository.findAll();
    }

    @Transactional
    public void update(User updatedUser) {
        User user = usersRepository.findByUsername(updatedUser.getUsername());
        if (!updatedUser.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        }
        user.setName(updatedUser.getName());
        user.setYears(updatedUser.getYears());
        user.setUsername(updatedUser.getUsername());
        user.setRoles((Set<Role>) updatedUser.getRoles());
        usersRepository.save(user);
    }
    @Transactional
    public User showUser(long id) {
        return usersRepository.getOne(id);
    }

    public User save(User user) {
        return usersRepository.save(user);
    }

    public User findByUsername(String username) {
        return usersRepository.findByUsername(username);
    }
}
