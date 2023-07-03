package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.repositories.UsersRepository;
import ru.kata.spring.boot_security.demo.service.RoleServiceImpl;

import java.security.Principal;

@Controller
@RequestMapping
public class UsersController {

    private final UsersRepository usersRepository;
    private final RoleServiceImpl roleService;
    @Autowired
    public UsersController(UsersRepository usersRepository, RoleServiceImpl roleService) {
        this.usersRepository = usersRepository;
        this.roleService = roleService;
    }
    @GetMapping("/user")
    public String userInfo(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = usersRepository.findByUsername(username);
        model.addAttribute("user", user);
        return "/user";
    }

    @GetMapping(path = "/api/auth", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<User> getAuthUser(@CurrentSecurityContext(expression = "authentication") Principal principal) {
        User user = usersRepository.findByUsername(principal.getName());
        return ResponseEntity.ok(user);
    }
}
