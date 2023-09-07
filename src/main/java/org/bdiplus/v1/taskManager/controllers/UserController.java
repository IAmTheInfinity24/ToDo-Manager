package org.bdiplus.v1.taskManager.controllers;

import org.bdiplus.v1.taskManager.entities.User;
import org.bdiplus.v1.taskManager.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/sign-in")
    public String signInPage() {
        return "users/sign-in";
    }

    @PostMapping("/sign-in")
    public String signIn(@RequestParam("email") String email,
                         @RequestParam("password") String password,
                         Model model) {
        if (userService.validatingUser(email, password)) {
            User user = userService.getUserByEmail(email);
            model.addAttribute("user", user);
            return "dashboard";
        }
        return "redirect:/users/sign-in";
    }

    @GetMapping("/sign-up")
    public String signUpPage(Model model) {
        model.addAttribute("user", new User());
        return "users/sign-up";
    }

    @PostMapping("/sign-up")
    public String signUp(@ModelAttribute("user") User user, Model model) {
        userService.createUser(user);
        model.addAttribute("user", user);
        return "dashboard";
    }

    @GetMapping
    public String getAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "user-list";
    }

    @GetMapping("/{userId}")
    public String getUserById(@PathVariable Long userId, Model model) {
        Optional<User> user = userService.getUserById(userId);
        user.ifPresent(u -> model.addAttribute("user", u));
        return "user-details";
    }

    @PostMapping("/{userId}/edit")
    public String updateUser(@PathVariable Long userId, @ModelAttribute("user") User user) {
        userService.updateUser(userId, user);
        return "redirect:/users";
    }

    @PostMapping("/{userId}/delete")
    public String deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return "redirect:/users";
    }

    @GetMapping("/home/{userId}")
    public String home(@PathVariable Long userId, Model model) {
        Optional<User> user = userService.getUserById(userId);
        user.ifPresent(u -> model.addAttribute("user", u));
        return "dashboard";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/";
    }
}
