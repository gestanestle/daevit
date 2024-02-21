package com.krimo.daevitserver.repository;

import com.krimo.daevitserver.model.LikeID;
import com.krimo.daevitserver.model.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<PostLike, LikeID> {

    @Query("SELECT COUNT(*) FROM PostLike l WHERE l.likeId.post = ?1")
    int countLikes(String postId);
}
