package org.bdiplus.v1.taskManager.repositories;

import org.bdiplus.v1.taskManager.entities.Task;
import org.bdiplus.v1.taskManager.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long> {

    List<Task> findByCreatedBy(User user);
}
