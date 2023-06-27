package back.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDto> getAllUsers() {

        return this.userRepository.findAll()
                .stream()
                .map(UserDto::fromEntity)
                .toList();

    }

    public UserDto getUserById(Long id) {

        Optional<UserEntity> userEntityOptional = userRepository.findById(id);

        return UserDto.fromEntity(userEntityOptional.orElseThrow());
    }

    public Set<UserDto> getUsersByEmailOrNickname(String email, String nickname) {

        Set<UserDto> userEntityByEmailSet = new HashSet<>();
        Set<UserDto> userEntityByNicknameSet;
        
        if (this.isValidStringParameter(email)) {
            userEntityByEmailSet = this.getAllUsersByEmail(email);
        }
        
        if (this.isValidStringParameter(nickname)) {
            userEntityByNicknameSet = this.getAllUsersByNickname(nickname);
            userEntityByEmailSet.addAll(userEntityByNicknameSet);
        }

        return userEntityByEmailSet;

    }

    public Set<UserDto> getAllUsersByEmail(String email) {

        if (!this.isValidStringParameter(email)) throw new InvalidParameterException("Email can't be null or blank or empty");

        return userRepository.findByEmailContaining(email)
                .stream()
                .map(UserDto::fromEntity)
                .collect(Collectors.toSet());
    }

    public Set<UserDto> getAllUsersByNickname(String nickname) {

        if (!this.isValidStringParameter(nickname)) throw new InvalidParameterException("Nickname can't be null or blank or empty");

        return userRepository.findByNicknameContaining(nickname)
                .stream()
                .map(UserDto::fromEntity)
                .collect(Collectors.toSet());
    }

    public UserDto getUserByEmail(String email) {

        Optional<UserEntity> userEntityOptional = userRepository.findByEmail(email);

        return UserDto.fromEntity(userEntityOptional.orElseThrow());
    }

    public UserDto getUserByNickname(String nickname) {

        Optional<UserEntity> userEntityOptional = userRepository.findByNickname(nickname);

        return UserDto.fromEntity(userEntityOptional.orElseThrow());
    }

    public void updateUser(UserDto userDto) {

    }

    private boolean isValidStringParameter(String parameter) {

        System.out.println(parameter + " =null: " + parameter == null);
        System.out.println(parameter + " isBlank(): " + parameter.isBlank());
        System.out.println(parameter + " isEmpty(): " + parameter.isEmpty());

        if (parameter == null) return false;

        if (parameter.isBlank() || parameter.isEmpty()) return false;

        return true;
    }
}
