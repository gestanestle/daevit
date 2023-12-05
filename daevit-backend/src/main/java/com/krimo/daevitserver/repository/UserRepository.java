package com.krimo.daevitserver.repository;

import com.krimo.daevitserver.model.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {

    @Modifying
    @Query("DELETE FROM User u WHERE u.authId = ?1")
    void deleteUserByAuthId(String authId);

    @Query("SELECT u FROM User u WHERE u.authId =?1")
    Optional<User> getUserByAuthId(String authId);
}
