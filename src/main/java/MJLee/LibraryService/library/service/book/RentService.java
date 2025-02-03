package MJLee.LibraryService.library.service.book;

import MJLee.LibraryService.library.dto.BookDto;
import MJLee.LibraryService.library.dto.UserDto;
import MJLee.LibraryService.library.entity.Book;
import MJLee.LibraryService.library.repository.BookRepository;
import MJLee.LibraryService.library.repository.UserRepository;
import MJLee.LibraryService.library.service.user.CreateUserService;
import MJLee.LibraryService.library.service.user.GetUserService;
import MJLee.LibraryService.library.service.user.ModifyUserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Date;

@Transactional
@Service
public class RentService {
    BookRepository repository;
    GetUserService getUserService;
    ModifyUserService modifyUserService;

    @Autowired
    public RentService(BookRepository repository, GetUserService getUserService, ModifyUserService modifyUserService){
        this.repository = repository;
        this.getUserService = getUserService;
        this.modifyUserService = modifyUserService;
    }

    public boolean rent(BookDto bookDto, UserDto user){
        Book bookFound = repository.findAll().stream()
                .filter(book -> book.getAuthor().equals(bookDto.getAuthor()))
                .filter(book -> book.getName().equals(bookDto.getName()))
                .findFirst()
                .orElse(null);


        if (bookFound == null) {
            return false;
        }

        if (bookFound.isRent()) {
            return false;
        }
        if(!getUserService.userCanRent(user)) return false;

        bookFound.setRent(true);
        Date date = new Date();
        bookFound.getUser().setStartRent(date);
        bookFound.getUser().setDeadlineRent(Date.from(date.toInstant().plus(Duration.ofDays(14))));
        modifyUserService.modifyUser(user);

        repository.save(bookFound);

        return true;
    }

    public boolean returned(BookDto bookDto){
        Book bookFound = repository.findAll().stream()
                .filter(book -> book.getAuthor().equals(bookDto.getAuthor()))
                .filter(book -> book.getName().equals(bookDto.getName()))
                .findFirst()
                .orElse(null);


        if (bookFound == null) {
            return false;
        }

        if (!bookFound.isRent()) {
            return false;
        }

        UserDto user = new UserDto();
        user.setName(bookFound.getUser().getUserName());
        user.setNickName(bookFound.getUser().getNickName());

        if(getUserService.deadLineOfUser(user).compareTo(new Date()) > 0){
            bookFound.getUser().setCanRent(false);
            modifyUserService.modifyUser(user);
        }

        bookFound.setRent(false);
        repository.save(bookFound);

        return true;
    }
}
