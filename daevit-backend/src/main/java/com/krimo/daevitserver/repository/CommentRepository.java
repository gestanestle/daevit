package com.krimo.daevitserver.repository;

import com.krimo.daevitserver.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("SELECT c FROM Comment c WHERE c.post.postId = ?1")
    Page<Comment> getByPostID(Long postId, Pageable pageable);

    @Query("SELECT c FROM Comment c WHERE c.parent.commentId = ?1")
    Page<Comment> getByParentComment(Long parentId, Pageable pageable);

    @Query("SELECT COUNT(*) FROM Comment c WHERE c.post.postId = ?1")
    int countComments(Long postId);

    @Query("SELECT COUNT(*) FROM Comment c WHERE c.commentId = ?1")
    int countLikes(Long id);

    @Query("SELECT c FROM Comment c WHERE c.author.username = ?1")
    Page<Comment> getCommentsBy(String username, Pageable pageable);
}
