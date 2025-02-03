package MJLee.LibraryService.library.repository;

import MJLee.LibraryService.library.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByNickName(String userNickName);
    void deleteByNickName(String nickName);
}
