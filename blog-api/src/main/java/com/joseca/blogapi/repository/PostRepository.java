package com.joseca.blogapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.joseca.blogapi.entity.Post;

@Repository // Opcional, asume que es repository por JPA
public interface PostRepository extends JpaRepository<Post, Long> {
}
