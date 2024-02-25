package com.krimo.daevitserver.service;

import com.krimo.daevitserver.dto.user.payload.UserData;
import com.krimo.daevitserver.model.Post;
import com.krimo.daevitserver.model.User;
import com.krimo.daevitserver.repository.PostRepository;
import com.krimo.daevitserver.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

public interface UserService {
    void createUser(UserData userData);
    User getUser(String authId);
    void deleteUser(String id);
    User getByUsername(String username);
}

@Service
@Transactional
@RequiredArgsConstructor
class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final LikeService likeService;
    private final CommentService commentService;
    private final ShareService shareService;


    @Override
    public void createUser(UserData userData) {
        String primaryEmail = userData.email_addresses().stream()
                .filter(e -> e.id().equals(userData.primary_email_address_id()))
                .findFirst().get().email_address();
        userRepository.save(
                new User(
                        userData.id(), userData.username(), primaryEmail,
                        userData.birthday(), userData.last_name(),
                        userData.first_name(), userData.image_url(),
                        LocalDateTime.ofInstant(Instant.ofEpochMilli(Long.parseLong(userData.created_at())), ZoneId.systemDefault()),
                        LocalDateTime.ofInstant(Instant.ofEpochMilli(Long.parseLong(userData.updated_at())), ZoneId.systemDefault())
                        )
        );
    }

    @Override
    public User getUser(String authId) {
        return userRepository.getUserByAuthId(authId).orElseThrow();
    }


    @Override
    public void deleteUser(String id) {
        userRepository.deleteUserByAuthId(id);
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.getByUsername(username).orElseThrow();
    }

}
