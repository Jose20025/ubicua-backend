package com.joseca.blogapi.service;

import com.joseca.blogapi.dto.PostDTO;
import com.joseca.blogapi.dto.PostResponseDTO;
import com.joseca.blogapi.entity.Post;

public interface PostService {
    PostDTO createPost(PostDTO postDTO);

    PostResponseDTO getAllPosts(int pageSize, int pageNumber);

    Post getPostById(Long id);

    Post updatePost(Long id, PostDTO postDTO);
}
