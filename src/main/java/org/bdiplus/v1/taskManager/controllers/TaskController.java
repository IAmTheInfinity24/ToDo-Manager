package org.bdiplus.v1.taskManager.controllers;


import org.bdiplus.v1.taskManager.entities.Task;
import org.bdiplus.v1.taskManager.entities.User;
import org.bdiplus.v1.taskManager.services.TaskService;
import org.bdiplus.v1.taskManager.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    private final UserService userService;


    @Autowired
    public TaskController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }



    @GetMapping("user/{userId}")
    public String getAllTasks(Model model, @PathVariable("userId") Long userId) {
        List<Task> tasks = taskService.getAllTasks();
        model.addAttribute("tasks", tasks);
        model.addAttribute("userId", userId);
        return "task-list";
    }

    @GetMapping("/{taskId}")
    public String getTaskById(@PathVariable Long taskId, Model model) {
        Optional<Task> task = taskService.getTaskById(taskId);
        task.ifPresent(t -> {
            model.addAttribute("task", t);
            User user = userService.getUserByTasksId(taskId);
            model.addAttribute("userId", user.getId());
        });
        return "task-details";
    }

    @GetMapping("/new/{userId}")
    public String createTaskForm(Model model, @PathVariable("userId") Long userId) {
        model.addAttribute("task", new Task());
        model.addAttribute("userId", userId);
        return "task-create";
    }
    @PostMapping("/{userId}")
    public String createTask(@ModelAttribute("task") Task task, Model model, @PathVariable("userId") Long userId) {
        User user = userService.getUserById(userId).orElseThrow();
        task.setCreatedBy(user);
        taskService.createTask(task);
        model.addAttribute("user", user);
        model.addAttribute("msg", "Task has been created with name: " + task.getTitle());
        return "dashboard";
    }

    @GetMapping("/{taskId}/edit")
    public String updateTaskForm(@PathVariable Long taskId, Model model) {
        Optional<Task> task = taskService.getTaskById(taskId);
        task.ifPresent(t -> {
            model.addAttribute("task", t);
            User user = userService.getUserByTasksId(taskId);
            model.addAttribute("userId", user.getId());
        });
        return "task-edit";
    }


   @PostMapping("/{taskId}/edit")
    public String updateTask(@PathVariable Long taskId, @ModelAttribute("task") Task task, Model model) {
        taskService.updateTask(taskId, task);
        List<Task> tasks = taskService.getAllTasks();
        model.addAttribute("tasks", tasks);
        return "task-list";
    }

    @GetMapping("/{taskId}/delete")
    public String deleteTaskForm(@PathVariable Long taskId, Model model) {
        Optional<Task> task = taskService.getTaskById(taskId);
        task.ifPresent(t -> {
            model.addAttribute("task", t);
            User user = userService.getUserByTasksId(taskId);
            model.addAttribute("userId", user.getId());
        });
        return "task-delete";
    }

    @PostMapping("/{taskId}/delete")
    public String deleteTask(@PathVariable Long taskId, Model model) {
        User user = userService.getUserByTasksId(taskId);
        taskService.deleteTask(taskId);
        model.addAttribute("user", user);
        model.addAttribute("msg", "Task has been deleted with taskId: " + taskId);
        return "dashboard";
    }
}
