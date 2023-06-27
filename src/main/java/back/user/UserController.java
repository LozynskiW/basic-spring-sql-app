package back.user;

import back.user.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@RestController
@RequestMapping(path = "/user")
class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/all")
    public List<UserDto> getAllUsers() {

        return this.userService.getAllUsers();
    }

    @RequestMapping(method = RequestMethod.GET)
    public Set<UserDto> getUserByEmailOrNickname(@RequestParam(required = false) String email, @RequestParam(required = false) String nickname) {

        try {
            return this.userService.getUsersByEmailOrNickname(email, nickname);

        } catch (NoSuchElementException e) {

            throw new UserNotFoundException();
        }
    }

    @RequestMapping(path = "/nickname", method = RequestMethod.GET)
    public UserDto getUserByNickname(@RequestParam String nickname) {

        try {
            return this.userService.getUserByNickname(nickname);

        } catch (NoSuchElementException e) {

            throw new UserNotFoundException();
        }
    }
}
