package com.krimo.daevitserver.repository;

import com.krimo.daevitserver.model.Post;
import com.krimo.daevitserver.model.PostShare;
import com.krimo.daevitserver.model.ShareID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface ShareRepository extends JpaRepository<PostShare, ShareID> {

    @Query("SELECT COUNT(*) FROM PostShare s WHERE s.shareId.post.postId = ?1")
    int countShares(Long postId);

    @Query("SELECT s.shareId.post FROM PostShare s WHERE s.shareId.sharedBy.username = ?1")
    Page<Post> getSharedBy(String username, Pageable pageable);
}
