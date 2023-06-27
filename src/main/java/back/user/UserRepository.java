package back.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import java.util.Set;

import java.util.List;
import java.util.Optional;

@Repository
interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(@NonNull String email);

    Optional<UserEntity> findByNickname(@NonNull String nickname);

    Set<UserEntity> findByEmailContaining(@NonNull String email);

    Set<UserEntity> findByNicknameContaining(@NonNull String nickname);

}
