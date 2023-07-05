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

    @RequestMapping(method = RequestMethod.GET, path = "/{userId}")
    public UserDto getUserById(@PathVariable long userId) {

        return this.userService.getUserById(userId);
    }

    @RequestMapping(method = RequestMethod.GET)
    public Set<UserDto> getUserByEmailOrNickname(@RequestParam(required = false) String email, @RequestParam(required = false) String nickname) {

        try {
            return this.userService.getUsersByEmailOrNickname(email, nickname);

        } catch (NoSuchElementException e) {

            throw new UserNotFoundException();
        }
    }

    @RequestMapping(path = "/new", method = RequestMethod.POST)
    public void addNewUser(@RequestBody UserDto userDto) {

        this.userService.addNewUser(userDto);

    }

    @RequestMapping(path = "/{userId}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable long userId) {

        try {
            this.userService.deleteUser(userId);
        } catch (NoSuchElementException e) {

            throw new UserNotFoundException("No such user to delete");
        }


    }

    @RequestMapping(path = "/{userId}", method = RequestMethod.PUT)
    public void updateUser(@PathVariable long userId, @RequestBody UserDto userDto) {

        try {

            this.userService.updateUser(userId, userDto);
        } catch (NoSuchElementException e) {

            throw new UserNotFoundException("No such user to update");
        }

    }
}
