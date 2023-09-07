package org.bdiplus.v1.taskManager.exceptionHandling.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {


    public ResourceNotFoundException(String Message) {
        super(Message);
    }

}