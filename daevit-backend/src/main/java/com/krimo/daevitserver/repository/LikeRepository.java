package com.krimo.daevitserver.repository;

import com.krimo.daevitserver.model.Post;
import com.krimo.daevitserver.model.LikeID;
import com.krimo.daevitserver.model.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface LikeRepository extends JpaRepository<PostLike, LikeID> {

    @Query("SELECT COUNT(*) FROM PostLike l WHERE l.likeId.post.postId = ?1")
    int countLikes(Long postId);

    @Query("SELECT l.likeId.post FROM PostLike l WHERE l.likeId.likedBy.username = ?1")
    Page<Post> getLikedBy(String username, Pageable pageable);
}
