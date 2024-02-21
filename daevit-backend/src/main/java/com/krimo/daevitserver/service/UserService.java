package com.krimo.daevitserver.service;

import com.krimo.daevitserver.dto.user.payload.UserData;
import com.krimo.daevitserver.model.User;
import com.krimo.daevitserver.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public interface UserService {
    void createUser(UserData userData);
    User getUser(String authId);
    void deleteUser(String id);
}

@Service
@Transactional
class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

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
        User user = userRepository.getUserByAuthId(authId).orElseThrow();
        return user;
//        return new UserDTO(user.getAuthId(), user.getUsername(), user.getLastName(), user.getFirstName(), user.getProfileImageURL());
    }


    @Override
    public void deleteUser(String id) {
        userRepository.deleteUserByAuthId(id);
    }

}
