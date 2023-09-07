package org.bdiplus.v1.taskManager.exceptionHandling.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    ResponseEntity<String> resourceNotFoundExceptionHandler(ResourceNotFoundException x) {
        return new ResponseEntity<>(x.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TaskNotFoundException.class)
    ResponseEntity<String> taskNotFoundExceptionHandler(TaskNotFoundException x) {
        return new ResponseEntity<>(x.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    ResponseEntity<String> userNotFoundExceptionHandler(UserNotFoundException x) {
        return new ResponseEntity<>(x.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NullPointerException.class)
    ResponseEntity<String> NullPointerExceptionHandler(NullPointerException x) {
        return new ResponseEntity<>(x.getMessage(), HttpStatus.NOT_FOUND);
    }
}
