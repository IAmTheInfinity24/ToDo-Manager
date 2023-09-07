package org.bdiplus.v1.taskManager.repositories;

import org.bdiplus.v1.taskManager.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

    User findByEmail(String email);


    Optional<User> findByTasksId(Long taskId);
}
