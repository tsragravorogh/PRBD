package com.tsragravorogh.PhotoService.repository;

import com.tsragravorogh.PhotoService.model.Album;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepository extends CrudRepository<Album, Integer> {

    @Query(value = "select a.id, a.name from albums a join users_albums ua on a.id = ua.id_albums join users u on ua.id_user = u.id where u.id = :userId", nativeQuery = true)
    List<Album> findAllByUserId(@Param("userId") int userId);
}
