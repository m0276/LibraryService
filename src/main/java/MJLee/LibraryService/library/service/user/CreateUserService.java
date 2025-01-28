package MJLee.LibraryService.library.service.user;

import MJLee.LibraryService.library.dto.UserDto;
import MJLee.LibraryService.library.entity.User;
import MJLee.LibraryService.library.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.DateUtils;

import java.time.*;
import java.util.Calendar;
import java.util.Date;
@Transactional
@Service
public class CreateUserService {
    UserRepository repository;

    public CreateUserService(UserRepository repository) {
        this.repository = repository;
    }

    public void createUser(UserDto userDto){
        User user = new User();
        Date date = new Date();
        user.setUserName(userDto.getName());
        user.setNickName(userDto.getNickName());
        user.setCreateUserTime(date);
        user.setDeadlineRent(Date.from(date.toInstant().plus(Duration.ofDays(14))));

        if(repository.findByNickName(userDto.getNickName()).isEmpty()){
            repository.save(user);
        }
    }


}
