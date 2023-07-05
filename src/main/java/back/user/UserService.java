package back.user;

import back.security.HashingAlgorithms;
import back.user.exceptions.UserAlreadyExistsException;
import back.user.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.*;
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
        
        if (UserUtil.isValidStringParameter(email)) {
            userEntityByEmailSet = this.getAllUsersByEmail(email);
        }
        
        if (UserUtil.isValidStringParameter(nickname)) {
            userEntityByNicknameSet = this.getAllUsersByNickname(nickname);
            userEntityByEmailSet.addAll(userEntityByNicknameSet);
        }

        return userEntityByEmailSet;

    }

    private Set<UserDto> getAllUsersByEmail(String email) {

        if (!UserUtil.isValidStringParameter(email)) throw new InvalidParameterException("Email can't be null or blank or empty");

        return userRepository.findByEmailContaining(email)
                .stream()
                .map(UserDto::fromEntity)
                .collect(Collectors.toSet());
    }

    private Set<UserDto> getAllUsersByNickname(String nickname) {

        if (!UserUtil.isValidStringParameter(nickname)) throw new InvalidParameterException("Nickname can't be null or blank or empty");

        return userRepository.findByNicknameContaining(nickname)
                .stream()
                .map(UserDto::fromEntity)
                .collect(Collectors.toSet());
    }

    private Optional<UserEntity> getUserByEmail(String email) {

        return userRepository.findByEmail(email);
    }

    public void addNewUser(UserDto userDto) {

        if (this.getUserByEmail(userDto.getEmail()).isPresent()) {

            throw new UserAlreadyExistsException();
        }

        userDto.setPasswordHash(HashingAlgorithms.stringToSHA256(userDto.getPassword()));
        userDto.setPassword("");

        this.userRepository.save(UserEntity.fromDTO(userDto));
    }

    public void updateUser(long userId, UserDto userToUpdate) {

        UserDto userBeforeUpdate = this.getUserById(userId);

        UserDto userAfterUpdate = UserUtil.overrideUserDtoValues(userBeforeUpdate, userToUpdate);

        System.out.println(userToUpdate);
        System.out.println(userBeforeUpdate);
        System.out.println(userAfterUpdate);

        if (userAfterUpdate.getPassword() != null) {
            userAfterUpdate.setPasswordHash(HashingAlgorithms.stringToSHA256(userAfterUpdate.getPassword()));
            userAfterUpdate.setPassword("");
        } else {
            userAfterUpdate.setPasswordHash(userBeforeUpdate.getPasswordHash());
        }

        this.userRepository.save(UserEntity.fromDTO(userAfterUpdate));
    }

    public void deleteUser(long userId) {

        UserDto userToDelete = this.getUserById(userId);

        this.userRepository.delete(UserEntity.fromDTO(userToDelete));
    }
}
