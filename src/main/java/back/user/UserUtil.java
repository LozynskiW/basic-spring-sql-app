package back.user;

import java.util.Objects;

class UserUtil {

    public static UserDto overrideUserDtoValues(UserDto baseUserDto, UserDto overridingUserDto) {

        return UserDto.builder()
                .id(baseUserDto.getId())
                .name((overridingUserDto.getName()) == null ? baseUserDto.getName() : overridingUserDto.getName())
                .surname((overridingUserDto.getSurname()) == null ? baseUserDto.getSurname() : overridingUserDto.getSurname())
                .email((overridingUserDto.getEmail()) == null ? baseUserDto.getEmail() : overridingUserDto.getEmail())
                .nickname((overridingUserDto.getNickname()) == null ? baseUserDto.getName() : overridingUserDto.getNickname())
                .password((overridingUserDto.getPassword()) == null ? baseUserDto.getPassword() : overridingUserDto.getPassword())
                .build();

    }

    public static boolean isValidStringParameter(String parameter) {

        System.out.println(parameter + " =null: " + parameter == null);
        System.out.println(parameter + " isBlank(): " + parameter.isBlank());
        System.out.println(parameter + " isEmpty(): " + parameter.isEmpty());

        if (parameter == null) return false;

        if (parameter.isBlank() || parameter.isEmpty()) return false;

        return true;
    }
}
