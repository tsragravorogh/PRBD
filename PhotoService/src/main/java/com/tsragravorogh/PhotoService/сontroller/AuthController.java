package com.tsragravorogh.PhotoService.сontroller;

import com.tsragravorogh.PhotoService.model.User;
import com.tsragravorogh.PhotoService.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    private final UserService  userService;
    private int id = 0;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/{email}")
    public String findUserByEmail(@PathVariable("email") String email, Model model) {
        User userFromDb = userService.findOneUser(email);
        if(userFromDb != null) {
            model.addAttribute("user", userService.findOneUser(email));
            return "profile";
        }else {
            return "login";
        }
    }

    @GetMapping("/users/new")
    public String register(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "registration";
    }


    @PostMapping("/registration")
    public String addUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        System.out.println("user is trying to register");
        return "login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user, Model model) {
        User userFromDb = userService.login(user);

        if(userFromDb != null) {
            this.id = userFromDb.getId();
            return "redirect:/profile";
        }else {
            return "login";
        }
    }

}
