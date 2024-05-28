package org.bdiplus.v1.taskManager.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.bdiplus.v1.taskManager.entities.Task;
import org.bdiplus.v1.taskManager.entities.User;
import org.bdiplus.v1.taskManager.payloads.TaskDTO;
import org.bdiplus.v1.taskManager.services.TaskService;
import org.bdiplus.v1.taskManager.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Task Controller", description = "Task management APIs")
@RestController
@RequestMapping("/apis/tasks")
public class RestTaskController {

    private final TaskService taskService;
    private final UserService userService;
    
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public RestTaskController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @Operation(
            summary = "Retrieve all tasks by Id",
            description = "Get all Tasks ")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Task.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("user/{userId}")
    public ResponseEntity<List<Task>> getAllTasks(@PathVariable("userId") Long userId) throws Exception {
        Optional<User> opUser= Optional.ofNullable(userService.getUserById(userId).orElseThrow(() -> new Exception("User not found Exception")));
        User user = opUser.get();
        List<Task> tasks = taskService.getAllTasksByUser(user);
        return ResponseEntity.ok(tasks);
    }

    @Operation(
            summary = "Retrieve a task by Id",
            description = "Get a Task object by specifying its id. The response is Task object with id, title, description.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Task.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/{taskId}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long taskId) {
        Optional<Task> task = taskService.getTaskById(taskId);
        return task.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Create a new task for a user")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Task.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping("/new/{userId}")
    public ResponseEntity<Task> createTask(@RequestBody TaskDTO taskDTO, @PathVariable("userId") Long userId) {
        User user = userService.getUserById(userId).orElseThrow();
        Task task = modelMapper.map(taskDTO, Task.class);
        task.setCreatedBy(user);
        taskService.createTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(task);
    }

    @Operation(
            summary = "Update a task by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Task.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PutMapping("/{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable Long taskId, @RequestBody Task task) {
        taskService.updateTask(taskId, task);
        return ResponseEntity.ok(task);
    }

    @Operation(
            summary = "Delete a task by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Task.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @DeleteMapping("/{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable Long taskId) {
        User user = userService.getUserByTasksId(taskId);
        taskService.deleteTask(taskId);
        return ResponseEntity.ok("Task has been deleted with taskId: " + taskId);
    }
}
