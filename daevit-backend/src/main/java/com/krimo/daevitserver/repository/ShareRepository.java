package com.krimo.daevitserver.repository;

import com.krimo.daevitserver.model.PostShare;
import com.krimo.daevitserver.model.ShareID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ShareRepository extends JpaRepository<PostShare, ShareID> {

    @Query("SELECT COUNT(*) FROM PostShare s WHERE s.shareId.post.postId = ?1")
    int countShares(Long postId);
}
