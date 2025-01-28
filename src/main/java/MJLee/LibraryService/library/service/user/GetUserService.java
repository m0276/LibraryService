package MJLee.LibraryService.library.service.user;

import MJLee.LibraryService.library.dto.UserDto;
import MJLee.LibraryService.library.entity.User;
import MJLee.LibraryService.library.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class GetUserService {
    UserRepository repository;

    public GetUserService(UserRepository repository) {
        this.repository = repository;
    }

    public boolean userCanRent(UserDto userDto){
        if(repository.findByNickName(userDto.getNickName()).isEmpty()) return false;
        else return repository.findByNickName(userDto.getNickName()).get().isCanRent();
    }

    public Date deadLineOfUser(UserDto user){
        if(repository.findByNickName(user.getNickName()).isEmpty()) return null;
        else return repository.findByNickName(user.getNickName()).get().getDeadlineRent();
    }

    public Optional<User> findByNickName(String nickName){
        return repository.findByNickName(nickName);
    }

}
