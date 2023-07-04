package back.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Builder
@Getter
@Setter
class UserDto {
    private long id;
    private String nickname;
    private String name;
    private String surname;
    private String email;
    private String passwordHash;
    private String password;

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDto userDto)) return false;

        if (id != userDto.id) return false;
        if (!Objects.equals(nickname, userDto.nickname)) return false;
        if (!Objects.equals(name, userDto.name)) return false;
        if (!Objects.equals(surname, userDto.surname)) return false;
        return (Objects.equals(email, userDto.email));
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (nickname != null ? nickname.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    public static UserDto fromEntity(UserEntity userEntity) {

        return new UserDtoBuilder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .surname(userEntity.getSurname())
                .nickname(userEntity.getNickname())
                .email(userEntity.getEmail())
                .passwordHash(userEntity.getPasswordHash())
                .build();
    }
}
