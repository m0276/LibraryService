package MJLee.LibraryService.library.service.user;

import MJLee.LibraryService.library.dto.UserDto;
import MJLee.LibraryService.library.entity.User;
import MJLee.LibraryService.library.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class DeleteUserServiceTest {

    @Autowired
    UserRepository repository;

    @Autowired
    DeleteUserService deleteUserService;

    @Transactional
    @Test
    void deleteUser() {
        User user = new User();
        user.setUserName("user1");
        user.setNickName("nickname1");
        user.setCreateUserTime(new Date());
        if(repository.findByNickName(user.getNickName()).isEmpty()){
            repository.save(user);
        }

        deleteUserService.deleteUser("nickname1");

        System.out.println("true");;
    }
}