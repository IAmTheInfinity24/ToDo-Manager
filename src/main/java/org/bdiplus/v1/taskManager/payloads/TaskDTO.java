package org.bdiplus.v1.taskManager.payloads;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class TaskDTO {

    @NotBlank(message = "Title is required")
    @Size(max = 100, message = "Title can be at most 100 characters long")
    private String title;

    @Size(max = 500, message = "Description can be at most 500 characters long")
    private String description;

    @FutureOrPresent(message = "Due date must be in the future or present")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dueDate;

    private boolean completed;

}
