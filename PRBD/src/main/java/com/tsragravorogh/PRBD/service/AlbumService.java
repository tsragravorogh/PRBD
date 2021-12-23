package com.tsragravorogh.PRBD.service;

import com.tsragravorogh.PRBD.model.Album;
import com.tsragravorogh.PRBD.model.Photo;
import com.tsragravorogh.PRBD.repository.AlbumRepository;
import com.tsragravorogh.PRBD.repository.PhotoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AlbumService {

    private Logger logger = LoggerFactory.getLogger(AlbumService.class);

    private final AlbumRepository albumRepository;

    private final PhotoRepository photoRepository;

    public AlbumService(AlbumRepository albumRepository, PhotoRepository photoRepository) {
        this.albumRepository = albumRepository;
        this.photoRepository = photoRepository;
    }

    public Album saveAlbum(String name, Integer userId) {
        System.out.println(name);
        Album album = new Album();
        album.setName(name);
        album.setUserId(userId);
        return albumRepository.save(album);
    }

    public List<Photo> findAllPhotosByAlbumId(Integer id) {
        List<Photo> listPhoto = photoRepository.findAllByAlbumId(id);
        return listPhoto;
    }

    public List<Album> getAlbumsByUserId(Integer id) {
        return albumRepository.findAllByUserId(id);
    }

    public void updateAlbum(Album album) {
        albumRepository.save(album);
    }

    public Album getAlbumById(Integer id) {
        return albumRepository.findById(id).orElse(null);
    }

    public void deleteAlbumById(Integer id) {
        albumRepository.deleteById(id);
    }
}
