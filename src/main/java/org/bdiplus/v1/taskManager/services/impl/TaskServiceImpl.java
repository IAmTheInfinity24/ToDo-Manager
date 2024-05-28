package org.bdiplus.v1.taskManager.services.impl;

import org.bdiplus.v1.taskManager.entities.Task;
import org.bdiplus.v1.taskManager.entities.User;
import org.bdiplus.v1.taskManager.repositories.TaskRepo;
import org.bdiplus.v1.taskManager.repositories.UserRepo;
import org.bdiplus.v1.taskManager.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepo taskRepo;
    @Autowired
    private UserRepo userRepo;

    @Autowired
    public TaskServiceImpl(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }

    @Override
    public List<Task> getAllTasksByUser(User user) {
        return taskRepo.findByCreatedBy(user);
    }

    @Override
    public Optional<Task> getTaskById(Long taskId) {
        return taskRepo.findById(taskId);
    }

    @Override
    public Task createTask(Task task) {
        return taskRepo.save(task);
    }

    @Override
    public Task updateTask(Long taskId, Task task) {
        if (taskRepo.existsById(taskId)) {
            Optional<User> optionalUser = this.userRepo.findByTasksId(taskId);
            User user = optionalUser.get();
            task.setCreatedBy(user);
            task.setId(taskId);
            return taskRepo.save(task);
        }
        return null;
    }

    @Override
    public void deleteTask(Long taskId) {
        taskRepo.deleteById(taskId);
    }


}

