package com.tsragravorogh.PRBD.repository;

import com.tsragravorogh.PRBD.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    Post getPostById(Integer id);

    List<Post> findAllByUserId(Integer id);
}
