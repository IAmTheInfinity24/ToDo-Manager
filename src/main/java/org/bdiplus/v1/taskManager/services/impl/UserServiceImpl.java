package org.bdiplus.v1.taskManager.services.impl;

import org.bdiplus.v1.taskManager.entities.User;
import org.bdiplus.v1.taskManager.repositories.UserRepo;
import org.bdiplus.v1.taskManager.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public Optional<User> getUserById(Long userId) {
        return userRepo.findById(userId);
    }

    @Override
    public User createUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public User updateUser(Long userId, User user) {
        // Ensure the user with userId exists in the database
        if (userRepo.existsById(userId)) {
            user.setId(userId);
            return userRepo.save(user);
        }
        return null; // User not found
    }

    @Override
    public void deleteUser(Long userId) {
        userRepo.deleteById(userId);
    }

    @Override
    public boolean validatingUser(String email, String password) {

        if (userRepo.existsByEmail(email)) {
            User user = this.userRepo.findByEmail(email);
            return user.getPassword().equals(password);
        }
        return false;
    }

    @Override
    public User getUserByEmail(String email) {
        User user = this.userRepo.findByEmail(email);
        return user;
    }

    @Override
    public User getUserByTasksId(Long taskId) {
        Optional<User> optionalUser = this.userRepo.findByTasksId(taskId);
        return optionalUser.get();

    }
}
