package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.UsersServiceImpl;
import ru.kata.spring.boot_security.demo.util.UserErrorResponse;
import ru.kata.spring.boot_security.demo.util.UserNonCreatedException;
import ru.kata.spring.boot_security.demo.util.UserNonEditException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/users")
public class AdminRestController {

    private final UsersServiceImpl usersService;


    public AdminRestController(UsersServiceImpl usersService) {
        this.usersService = usersService;
    }
    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(usersService.read(), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public User findById(@PathVariable long userId) {
        return usersService.showUser(userId);
    }

    @PostMapping()
    public ResponseEntity<HttpStatus> createUser(@RequestBody User user) {
        usersService.add(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> updateUser(@RequestBody User user) {
        usersService.update(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<HttpStatus> deleteUser(@RequestParam("id") long id) {
        usersService.deleteById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> handleException(UserNonCreatedException e) {
        UserErrorResponse response = new UserErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> handleException(UserNonEditException e) {
        UserErrorResponse response = new UserErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}