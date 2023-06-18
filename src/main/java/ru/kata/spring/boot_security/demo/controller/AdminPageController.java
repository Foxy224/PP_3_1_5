package ru.kata.spring.boot_security.demo.controller;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.Users;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UsersService;


@Controller
@RequestMapping("/admin")
public class AdminPageController {

	private final UsersService userService;
	private final RoleService roleService;

	public AdminPageController(UsersService userService, RoleService roleService) {
		this.userService = userService;
		this.roleService = roleService;
	}


	@GetMapping()
	public String printUsers(ModelMap model) {

		model.addAttribute("admin", userService.read());

		return "/admin";
	}

	@GetMapping("/new")
	public String addUser(Model model) {
		model.addAttribute("user", new Users());
		model.addAttribute("allRoles", roleService.getAllRoles());

		return "/new";
	}

	@PostMapping("/save")
	public String create(@ModelAttribute("user") Users users) {
		userService.add(users);
		return "redirect:/admin";
	}

	@GetMapping("/edit")
	public String edit(Model model, @RequestParam long id) {
		model.addAttribute("user", userService.showUser(id));
		return "/edit";
	}


	@PostMapping("/edit")
	public String update(@ModelAttribute("admin") Users user, @RequestParam("id") long id) {
		user.setId(id);
		userService.update(user);
		return "redirect:/admin";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam long id) {
		userService.delete(id);
		return "redirect:/admin";
	}

}