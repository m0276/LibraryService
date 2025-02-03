package MJLee.LibraryService.library.service.user;

import MJLee.LibraryService.library.dto.UserDto;
import MJLee.LibraryService.library.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Transactional
@Service
public class ModifyUserService {
    UserRepository repository;

    @Autowired
    public ModifyUserService(UserRepository repository) {
        this.repository = repository;
    }

    public void modifyUser(UserDto user){
        if(repository.findByNickName(user.getNickName()).isPresent()) repository.save(repository.findByNickName(user.getNickName()).get());
    }

    public void changeUserName(String newName, String nickName){

    }
}
