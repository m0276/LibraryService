package MJLee.LibraryService.library.service.user;

import MJLee.LibraryService.library.dto.UserDto;
import MJLee.LibraryService.library.entity.User;
import MJLee.LibraryService.library.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class GetUserService {
    UserRepository repository;

    @Autowired
    public GetUserService(UserRepository repository) {
        this.repository = repository;
    }

    public boolean userCanRent(UserDto userDto){
        if(repository.findByNickName(userDto.getNickName()).isEmpty()
                || repository.findByNickName(userDto.getNickName()).get().getDelayDays() > 0) return false;
        else{
            return repository.findByNickName(userDto.getNickName()).get().isCanRent()
                    && repository.findByNickName(userDto.getNickName()).get().getDelayDays() <= 0;
        }
    }


    public Optional<User> findByNickName(String nickName){
        return repository.findByNickName(nickName);
    }

}
