package com.tsragravorogh.PRBD.controller;

import com.tsragravorogh.PRBD.model.Album;
import com.tsragravorogh.PRBD.model.Photo;
import com.tsragravorogh.PRBD.model.User;
import com.tsragravorogh.PRBD.service.AlbumService;
import com.tsragravorogh.PRBD.service.PhotoService;
import com.tsragravorogh.PRBD.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AlbumsController {

    private Logger logger = LoggerFactory.getLogger(AlbumsController.class);

    private final AlbumService albumService;

    private final PhotoService photoService;

    public AlbumsController(AlbumService albumService, PhotoService photoService) {
        this.albumService = albumService;
        this.photoService = photoService;
    }

    @GetMapping("/albums/new")
    public String createAlbumFrom(Model model) {
        model.addAttribute("album", new Album());
        return "create_album";
    }

    @PostMapping("/albums")
    public String saveAlbum(@ModelAttribute("album") Album album, Model model) {
        albumService.saveAlbum(album.getName(), UserService.getUser().getId());
        model.addAttribute("user", UserService.getUser());
        List<Album> userAlbumsByUserId = albumService.getAlbumsByUserId(UserService.getUser().getId());
        UserService.getUser().setUserAlbums(userAlbumsByUserId);
        model.addAttribute("albums", userAlbumsByUserId);
        return "personal_page";
    }

    @GetMapping("/albums/{id}")
    public String getAlbumsById(@PathVariable Integer id, Model model) {
        List<Photo> listPhoto = albumService.findAllPhotosByAlbumId(id);
//        List<String> links = new ArrayList<>();
//        for (int i = 0; i < listPhoto.size(); i++) {
//            links.add(listPhoto.get(i).getLink());
//        }
//        model.addAttribute("links", links);
        model.addAttribute("photos", listPhoto);
        model.addAttribute("albumId", id);
        return "photos_album";
    }

    @GetMapping("/albums/edit/{id}")
    public String editAlbumForm(@PathVariable Integer id, Model model) {
        Album album = albumService.getAlbumById(id);
        if(album == null) {
            return "error_page";
        }
        model.addAttribute("album", album);
        return "edit_album";
    }

    @PostMapping("/album/edit/{id}")
    public String editAlbum(@PathVariable Integer id, @ModelAttribute("album") Album album, Model model) {
        Album existingAlbum = albumService.getAlbumById(id);
        if(album == null) {
            return "error_page";
        }
        existingAlbum.setName(album.getName());
        albumService.updateAlbum(existingAlbum);
        List<Album> albumsByUserId = albumService.getAlbumsByUserId(UserService.getUser().getId());
        model.addAttribute("user", UserService.getUser());
        UserService.getUser().setUserAlbums(albumsByUserId);
        model.addAttribute("albums", albumsByUserId);
        return "personal_page";
    }

    @GetMapping("albums/delete/{id}")
    public String deleteAlbum(@PathVariable("id") String id, Model model) {
        albumService.deleteAlbumById(Integer.parseInt(id));
        List<Album> albums = albumService.getAlbumsByUserId(UserService.getUser().getId());
        UserService.getUser().setUserAlbums(albums);
        model.addAttribute("user", UserService.getUser());
        model.addAttribute("albums", albums);
        return "personal_page";
    }
}
