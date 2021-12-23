package com.tsragravorogh.PRBD.controller;

import com.tsragravorogh.PRBD.model.Album;
import com.tsragravorogh.PRBD.model.User;
import com.tsragravorogh.PRBD.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UsersController {

    private Logger logger = LoggerFactory.getLogger(UsersController.class);

    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("registerRequest", new User());
        logger.info("Register page open");
        return "register_page";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        model.addAttribute("loginRequest", new User());
        logger.info("Login page opened");
        return "login_page";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user) {
        logger.info("User are registrating");
        User registerUser = userService.registerUser(user.getName(), user.getSurname(), user.getEmail(), user.getPassword());
        return registerUser == null ? "error_page" : "redirect:/login";
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        User user = UserService.getUser();
        List<Album> albums = user.getUserAlbums();
        model.addAttribute("albums", albums);
        model.addAttribute("user", user);
        model.addAttribute("count", albums.size());
        return "personal_page";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user, Model model) {
        User loginUser = userService.authenticate(user.getEmail(), user.getPassword());
        if(loginUser != null) {
            List<Album> userAlbums = loginUser.getUserAlbums();
            model.addAttribute("albums", userAlbums);
            model.addAttribute("user", loginUser);
            return "personal_page";
        }else {
            return "error_page";
        }
    }
}
