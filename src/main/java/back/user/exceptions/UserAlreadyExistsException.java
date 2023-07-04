package back.user.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException() {
        super("Email already taken by another user");
    }

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
