package back.user.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;
import java.util.function.Supplier;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends NoSuchElementException {

    public UserNotFoundException() {
        super("No such user found");
    }
}
