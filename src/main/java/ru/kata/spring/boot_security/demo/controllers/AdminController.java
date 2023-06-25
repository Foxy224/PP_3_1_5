package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.service.UsersService;

@Controller
public class AdminController {
    private final UsersService usersService;
    private final RoleServiceImpl roleService;

    @Autowired
    public AdminController(UsersService usersService, RoleServiceImpl roleService) {
        this.usersService = usersService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public String getAllUsers(Model model) {
        model.addAttribute("users", usersService.read());
        model.addAttribute("allRoles", roleService.getAllRoles());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = usersService.findByUsername(username);
        model.addAttribute("user", user);
        model.addAttribute("registrationUser", new User());
        model.addAttribute("editUser", new User());
        return "/admin";
    }


    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("allRoles", roleService.getAllRoles());
        return "/new";
    }

    @PostMapping("/save")
    public String create(@ModelAttribute("user") User user) {
        if (!("".equals(user.getName()))) {
            usersService.add(user);
        }
        return "redirect:/admin";
    }


    @GetMapping("/edit")
    public String edit(Model model, @RequestParam("id") long id) {
        model.addAttribute("user", usersService.showUser(id));
        model.addAttribute("allRoles", roleService.getAllRoles());
        return "/edit";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("user") User user) {
        usersService.update(user);
        return "redirect:/admin";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam long id) {
        usersService.delete(id);
        return "redirect:/admin";
    }


}
