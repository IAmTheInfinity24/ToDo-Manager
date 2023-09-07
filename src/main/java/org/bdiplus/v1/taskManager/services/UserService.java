package org.bdiplus.v1.taskManager.services;

import org.bdiplus.v1.taskManager.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();

    Optional<User> getUserById(Long userId);

    User createUser(User user);

    User updateUser(Long userId, User user);

    void deleteUser(Long userId);

    boolean validatingUser(String email, String password);

    User getUserByEmail(String email);

    User getUserByTasksId(Long taskId);
}
