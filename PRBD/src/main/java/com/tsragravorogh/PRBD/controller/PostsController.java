package com.tsragravorogh.PRBD.controller;

import com.tsragravorogh.PRBD.model.Post;
import com.tsragravorogh.PRBD.model.User;
import com.tsragravorogh.PRBD.service.PostService;
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
public class PostsController {

    private Logger logger = LoggerFactory.getLogger(UsersController.class);

    private final PostService postService;

    public PostsController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public String getPosts(Model model) {
        model.addAttribute("posts", postService.getAllPostByUserId(UserService.getUser().getId()));
        return "posts";
    }

    @PostMapping("/posts")
    public String savePost(@ModelAttribute("post") Post post) {
        post.setUserId(UserService.user.getId());
        postService.savePost(post);
        return "redirect:/posts";
    }

    @GetMapping("/posts/new")
    public String createPostForm(Model model) {
        Post post = new Post();
        model.addAttribute("post", post);
        return "create_post";
    }

    @GetMapping("/posts/edit/{id}")
    public String editPostForm(@PathVariable Integer id, Model model) {
        model.addAttribute("post", postService.getPostById(id));
        return "edit_post";
    }

    @PostMapping("/posts/{id}")
    public String updatePost(@PathVariable Integer id, @ModelAttribute("post") Post post) {
        Post existingPost = postService.getPostById(id);
        existingPost.setTitle(post.getTitle());
        existingPost.setBody(post.getBody());
        existingPost.setActivityId(post.getActivityId());

        postService.updatePost(existingPost);
        return "redirect:/posts";
    }

    @GetMapping("/posts/delete/{id}")
    public String deletePost(@PathVariable String id, Model model) {
        postService.deletePostById(Integer.parseInt(id));
        List<Post> posts = postService.getAllPostByUserId(UserService.getUser().getId());
        model.addAttribute("posts", posts);
        return "posts";
    }
}
