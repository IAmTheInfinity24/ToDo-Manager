package org.bdiplus.v1.taskManager.repositories;

import org.bdiplus.v1.taskManager.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long> {
}
