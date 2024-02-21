package com.krimo.daevitserver.repository;

import com.krimo.daevitserver.model.LikeID;
import com.krimo.daevitserver.model.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<PostLike, LikeID> {
}
