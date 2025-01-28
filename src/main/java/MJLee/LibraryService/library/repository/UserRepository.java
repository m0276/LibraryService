package MJLee.LibraryService.library.repository;

import MJLee.LibraryService.library.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByNickName(String userNickName);

    boolean deleteByNickName(String nickName);
}
