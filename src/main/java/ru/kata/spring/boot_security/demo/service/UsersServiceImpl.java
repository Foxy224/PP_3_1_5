package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.repositories.UsersRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {
    private final UsersRepository usersRepository;

    @Autowired
    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Transactional
    public void add(User user) {
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
    public void update(User user) {
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
