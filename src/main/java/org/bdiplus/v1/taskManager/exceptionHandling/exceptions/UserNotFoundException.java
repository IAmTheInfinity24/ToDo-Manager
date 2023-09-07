package org.bdiplus.v1.taskManager.exceptionHandling.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Getter
@Setter
public class UserNotFoundException extends UsernameNotFoundException {


    public UserNotFoundException(String Message) {
        super(Message);
    }


}