package com.joseca.blogapi.service;

import java.util.List;

import com.joseca.blogapi.dto.PostDTO;
import com.joseca.blogapi.entity.Post;

public interface PostService {
    PostDTO createPost(PostDTO postDTO);

    List<Post> getAllPosts();

    Post getPostById(Long id);

    Post updatePost(Long id, PostDTO postDTO);
}
