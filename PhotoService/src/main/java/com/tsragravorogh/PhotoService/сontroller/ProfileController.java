package com.tsragravorogh.PhotoService.сontroller;

import com.tsragravorogh.PhotoService.service.AlbumService;
import com.tsragravorogh.PhotoService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProfileController {

    @Autowired private UserService userService;

    @Autowired private AlbumService albumService;

    @GetMapping("/profile/{id}")
    public String profile(@PathVariable int id,  Model model) {
        model.addAttribute("albums", albumService.getAllAlbumsByUserId(id));
        return "profile";
    }
}
