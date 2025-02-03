package MJLee.LibraryService.library.service.user;

import MJLee.LibraryService.library.entity.User;
import MJLee.LibraryService.library.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
public class CreateUserServiceTest {
    @Autowired
    UserRepository repository;

    @Test
    void testA(){
        User user = new User();
        user.setUserName("name1");
        user.setNickName("user1");
        user.setCreateUserTime(new Date());

        if(repository.findByNickName("user1").isEmpty()){
            repository.save(user);
        }
    }

}