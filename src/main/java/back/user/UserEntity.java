package back.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name = "users", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;
    @Column(name = "user_nickname")
    private String nickname;
    @Column(name = "user_name")
    private String name;
    @Column(name = "user_surname")
    private String surname;
    @Column(name = "user_email")
    private String email;
    @Column(name = "user_passwordhash")
    private String passwordHash;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserEntity that)) return false;

        if (id != that.id) return false;
        if (!Objects.equals(nickname, that.nickname)) return false;
        if (!Objects.equals(name, that.name)) return false;
        if (!Objects.equals(surname, that.surname)) return false;
        if (!Objects.equals(email, that.email)) return false;
        return Objects.equals(passwordHash, that.passwordHash);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (nickname != null ? nickname.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (passwordHash != null ? passwordHash.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                '}';
    }

    public static UserEntity fromDTO(UserDto userDto) {

        UserEntity userEntity = new UserEntity();

        userEntity.setId(userDto.getId());
        userEntity.setName(userDto.getName());
        userEntity.setSurname(userDto.getSurname());
        userEntity.setNickname(userDto.getNickname());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setPasswordHash(userDto.getPasswordHash());

        return userEntity;
    }
}
