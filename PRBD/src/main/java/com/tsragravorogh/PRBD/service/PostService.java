package com.tsragravorogh.PRBD.service;

import com.tsragravorogh.PRBD.model.Post;
import com.tsragravorogh.PRBD.model.User;
import com.tsragravorogh.PRBD.repository.PostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post addPost(String title, String body, int activityId, User user) {
        if (title == null || body == null) {
            return null;
        } else {
            Post post = new Post();
            post.setTitle(title);
            post.setBody(body);
            post.setActivityId(activityId);
            logger.info("Post was added {}", post);
            return postRepository.save(post);
        }
    }

    public List<Post> getAllPosts() {
        List<Post> list = postRepository.findAll();
        return list;
    }

    public List<Post> getAllPostByUserId(Integer id) {
        List<Post> list = postRepository.findAllByUserId(id);
        return list;
    }

    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    public Post getPostById(Integer id) {
        return postRepository.getPostById(id);
    }

    public void updatePost(Post post) {
        postRepository.save(post);
    }

    public void deletePostById(Integer id) {
        postRepository.deleteById(id);
    }
}
