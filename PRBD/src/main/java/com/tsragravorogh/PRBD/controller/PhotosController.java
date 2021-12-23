package com.tsragravorogh.PRBD.controller;

import com.tsragravorogh.PRBD.model.Album;
import com.tsragravorogh.PRBD.model.Photo;
import com.tsragravorogh.PRBD.service.AlbumService;
import com.tsragravorogh.PRBD.service.PhotoService;
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
public class PhotosController {

    private Logger logger = LoggerFactory.getLogger(PhotosController.class);

    private final PhotoService photoService;

    private final AlbumService albumService;

    public PhotosController(PhotoService photoService, AlbumService albumService) {
        this.photoService = photoService;
        this.albumService = albumService;
    }

    @GetMapping("/photos/new/{albumId}")
    public String createPhotoForm(Model model, @PathVariable String albumId) {
        Photo photo = new Photo();
        Integer albumIdInt = Integer.parseInt(albumId);
        photo.setAlbumId(albumIdInt);
        model.addAttribute("photo", photo);
        model.addAttribute("albumId", albumId);
        return "create_photo";
    }

    @PostMapping("photos/{albumId}")
    public String savePhoto(@ModelAttribute("photo") Photo photo, Model model, @PathVariable String albumId) {
        photo.setAlbumId(Integer.parseInt(albumId));
        photoService.savePhoto(photo.getLink(), photo.getAlbumId());
        List<Photo> listPhoto = albumService.findAllPhotosByAlbumId(photo.getAlbumId());
        model.addAttribute("photos", listPhoto);
        model.addAttribute("albumId", photo.getAlbumId());
        return "photos_album";
    }

    @GetMapping("photos/delete/{photoId}/{albumId}")
    public String deletePhoto(@PathVariable("albumId") String albumId, @PathVariable("photoId") String photoId ,Model model) {
        photoService.deletePhotoById(Integer.parseInt(photoId));
        List<Photo> listPhoto = albumService.findAllPhotosByAlbumId(Integer.parseInt(albumId));
        model.addAttribute("photos", listPhoto);
        model.addAttribute("albumId", Integer.parseInt(albumId));
        return "photos_album";
    }
}
