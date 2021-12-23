package com.tsragravorogh.PRBD.service;

import com.tsragravorogh.PRBD.model.Photo;
import com.tsragravorogh.PRBD.repository.PhotoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoService {

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    private final PhotoRepository photoRepository;

    public PhotoService(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    public Photo savePhoto(String link, Integer albumId) {
        Photo photo = new Photo();
        photo.setLink(link);
        photo.setAlbumId(albumId);
        return this.photoRepository.save(photo);
    }

    public void deletePhotoById(Integer id) {
        this.photoRepository.deleteById(id);
    }
}
