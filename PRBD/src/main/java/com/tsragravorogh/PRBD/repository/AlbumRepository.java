package com.tsragravorogh.PRBD.repository;

import com.tsragravorogh.PRBD.model.Album;
import com.tsragravorogh.PRBD.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Integer> {

    List<Album> findAllByUserId(Integer id);
}
