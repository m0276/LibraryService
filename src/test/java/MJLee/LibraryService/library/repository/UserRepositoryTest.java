package MJLee.LibraryService.library.repository;

import MJLee.LibraryService.library.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    UserRepository repository;


    @Test
    public void save(){
        User user = new User();
        user.setUserName("user1");
        user.setNickName("nickname1");
        user.setCreateUserTime(new Date());

        if(repository.findByNickName("nickname1").isEmpty()) repository.save(user);
    }
}