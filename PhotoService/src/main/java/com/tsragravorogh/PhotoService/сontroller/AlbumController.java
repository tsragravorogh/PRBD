package com.tsragravorogh.PhotoService.сontroller;

import com.tsragravorogh.PhotoService.model.Album;
import com.tsragravorogh.PhotoService.repository.AlbumRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class AlbumController {
    private final AlbumRepository albumRepository;

    public AlbumController(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @GetMapping("/userAlbums/{userId}")
    public String getAllAlbumsByUserId(@PathVariable int userId, Model model) {
        List<Album> albumList = albumRepository.findAllByUserId(userId);
        model.addAttribute("albumList", albumList);
        return "albums";
    }
}
