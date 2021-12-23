package com.tsragravorogh.PRBD.repository;

import com.tsragravorogh.PRBD.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Integer> {
    List<Photo> findAllByAlbumId(Integer id);
}
