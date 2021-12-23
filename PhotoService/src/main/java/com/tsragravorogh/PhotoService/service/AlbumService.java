package com.tsragravorogh.PhotoService.service;

import com.tsragravorogh.PhotoService.model.Album;
import com.tsragravorogh.PhotoService.repository.AlbumRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumService {
    private final AlbumRepository albumRepository;

    public AlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    public List<Album> getAllAlbumsByUserId(int id) {
        return albumRepository.findAllByUserId(id);
    }
}
