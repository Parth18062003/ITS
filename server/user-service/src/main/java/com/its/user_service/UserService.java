package com.its.user_service;

import com.its.user_service.exception.InvalidInputException;
import com.its.user_service.exception.UserNotFoundException;
import com.its.user_service.monitoring.ActivityLogService;
import jakarta.validation.Valid;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ActivityLogService activityLogService;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, ActivityLogService activityLogService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.activityLogService = activityLogService;
    }

    @Cacheable(value = "its:users", key = "#pageable.pageNumber")
    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Cacheable(value = "its:users", key = "#id")
    public Optional<User> getUserById(UUID id) {
        return userRepository.findById(id);
    }

    @Cacheable(value = "its:users", key = "{#email, #username}")
    public Optional<User> getUserByEmailOrUsername(String email, String username) {
        return Optional.ofNullable(userRepository.findByUsernameOrEmail(email, username));
    }

    @CachePut(value = "its:users", key = "#user.id")
    public User saveUser(@Valid User user) {
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            throw new InvalidInputException("Username cannot be null or empty");
        }

        Role userRole = roleRepository.findByName("USER");
        if (userRole == null) {
            throw new RuntimeException("USER role not found in the database");
        }

        user.setRoles(new HashSet<>(Collections.singleton(userRole)));

        User savedUser = userRepository.save(user);
        activityLogService.createLog(
                savedUser.getId().toString(), // Convert UUID to String
                savedUser.getEmail(),
                "USER_CREATION",
                "User created with email: " + savedUser.getEmail()
        );

        return savedUser; // Return the saved user
    }

    @CachePut(value = "its:users", key = "#user.id")
    public User updateUser(@Valid User user) {
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            throw new InvalidInputException("Username cannot be null or empty");
        }

        Role userRole = roleRepository.findByName("USER");
        if (userRole == null) {
            throw new RuntimeException("USER role not found in the database");
        }

        user.setRoles(new HashSet<>(Collections.singleton(userRole)));

        User savedUser = userRepository.save(user);
        activityLogService.createLog(
                savedUser.getId().toString(), // Convert UUID to String
                savedUser.getEmail(),
                "USER_UPDATED",
                "User updated with email: " + savedUser.getEmail()
        );

        return savedUser; // Return the saved user
    }

    @CacheEvict(value = "its:users", allEntries = true)
    public void deleteUser(UUID id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id.toString());
        }

        activityLogService.createLog(
                id.toString(), // Convert UUID to String
                getUserById(id).map(User::getEmail).orElse("unknown"),
                "USER_DELETION",
                "User with ID: " + id + " was deleted"
        );

        userRepository.deleteById(id);
    }

    @CachePut(value = "its:users", key = "#userId")
    public User enable2FA(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId.toString()));
        user.setIs2faEnabled(true);

        activityLogService.createLog(
                userId.toString(), // Convert UUID to String
                user.getEmail(),
                "2FA_ENABLED",
                "2FA enabled for user with ID: " + userId
        );

        return userRepository.save(user);
    }

    @CachePut(value = "its:users", key = "#userId")
    public User disable2FA(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId.toString()));
        user.setIs2faEnabled(false);

        activityLogService.createLog(
                userId.toString(), // Convert UUID to String
                user.getEmail(),
                "2FA_DISABLED",
                "2FA disabled for user with ID: " + userId
        );

        return userRepository.save(user);
    }

    @CachePut(value = "its:users", key = "#userId")
    public User updateProfileImage(UUID userId, String imageUrl) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId.toString()));
        user.setProfileImageUrl(imageUrl);

        activityLogService.createLog(
                userId.toString(), // Convert UUID to String
                user.getEmail(),
                "PROFILE_IMAGE_UPDATED",
                "Profile image updated for user with ID: " + userId
        );

        return userRepository.save(user);
    }
}
