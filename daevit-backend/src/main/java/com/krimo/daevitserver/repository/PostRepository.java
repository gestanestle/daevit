package com.krimo.daevitserver.repository;

import com.krimo.daevitserver.model.Post;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p FROM Post p WHERE p.author.username = ?1")
    Page<Post> getPostsBy(String username, Pageable pageable);

}
