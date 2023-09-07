package org.bdiplus.v1.taskManager.exceptionHandling.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class TaskNotFoundException extends RuntimeException {


    public TaskNotFoundException(String Message) {
        super(Message);
    }

}