package com.joseca.blogapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joseca.blogapi.dto.PostDTO;
import com.joseca.blogapi.entity.Post;
import com.joseca.blogapi.exception.ResourceNotFoundException;
import com.joseca.blogapi.repository.PostRepository;
import com.joseca.blogapi.service.PostService;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    // Constructor
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @SuppressWarnings("null")
    @Override
    public PostDTO createPost(PostDTO postDTO) {
        Post post = mapToEntity(postDTO);

        Post newPost = postRepository.save(post);

        return mapToDTO(newPost);
    }

    @Override
    public List<Post> getAllPosts() {
        List<Post> posts = postRepository.findAll();

        return posts;
    }

    @Override
    @SuppressWarnings("null")
    public Post getPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", id.toString()));

        return post;

    }

    // Convertir un DTO a una entidad
    private Post mapToEntity(PostDTO postDTO) {
        Post post = new Post();

        // post.setId(postDTO.getId());
        post.setTitle(postDTO.getTitle());
        post.setDescription(postDTO.getDescription());
        post.setContent(postDTO.getContent());

        return post;
    }

    // Convertir de una entidad a un DTO
    private PostDTO mapToDTO(Post post) {
        PostDTO postDTO = new PostDTO();

        postDTO.setId(post.getId());
        postDTO.setTitle(post.getTitle());
        postDTO.setDescription(post.getDescription());
        postDTO.setContent(post.getContent());

        return postDTO;
    }

    @Override
    public Post updatePost(Long id, PostDTO postDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updatePost'");
    }

}
