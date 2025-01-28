package MJLee.LibraryService.library.service.user;

import MJLee.LibraryService.library.dto.UserDto;
import MJLee.LibraryService.library.entity.User;
import MJLee.LibraryService.library.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
@Transactional
@Service
public class DeleteUserService {
    UserRepository repository;

    public DeleteUserService(UserRepository repository) {
        this.repository = repository;
    }

    public boolean deleteUser(UserDto userDto){
        if(repository.findByNickName(userDto.getNickName()).isEmpty()) return false;
        else {
            return repository.deleteByNickName(userDto.getNickName());
        }
    }
}
