package MJLee.LibraryService.library.service.book;

import MJLee.LibraryService.library.dto.BookDto;
import MJLee.LibraryService.library.dto.UserDto;
import MJLee.LibraryService.library.entity.Book;
import MJLee.LibraryService.library.entity.User;
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
        if(getUserService.findByNickName(user.getNickName()).isEmpty()) return false;
        User rentingUser = getUserService.findByNickName(user.getNickName()).get();

        Date date = new Date();
        bookFound.setStartRent(date);
        bookFound.setDeadlineRent(Date.from(date.toInstant().plus(Duration.ofDays(14))));
        bookFound.setUser(rentingUser);

        rentingUser.setRentedBooks(rentingUser.getRentedBooks()+1);
        if(rentingUser.getRentedBooks() >= 3){
            rentingUser.setCanRent(false);
        }

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

        UserDto userDto = getUserDto(bookFound);

        modifyUserService.modifyUser(userDto);

        bookFound.setRent(false);
        bookFound.setStartRent(null);
        bookFound.setDeadlineRent(null);
        repository.save(bookFound);

        return true;
    }

    private static UserDto getUserDto(Book bookFound) {
        User user = bookFound.getUser();

        if(bookFound.getDeadlineRent().compareTo(new Date()) < 0){ // deadline이 현재 시간을 지났으면
            user.setCanRent(false);
            user.setDelayDays(Math.max(user.getDelayDays(),
                    new Date().getTime() - bookFound.getDeadlineRent().getTime()));
            user.setRentedBooks(user.getRentedBooks()-1);
        }
        else{
            user.setRentedBooks(user.getRentedBooks()-1);
            if(user.getRentedBooks() < 3){
                user.setCanRent(true);
            }
        }

        UserDto userDto = new UserDto();
        userDto.setName(user.getUserName());
        userDto.setNickName(user.getNickName());
        return userDto;
    }
}
