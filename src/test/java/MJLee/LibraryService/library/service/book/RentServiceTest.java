package MJLee.LibraryService.library.service.book;

import MJLee.LibraryService.library.dto.UserDto;
import MJLee.LibraryService.library.entity.Book;
import MJLee.LibraryService.library.entity.User;
import MJLee.LibraryService.library.repository.BookRepository;
import MJLee.LibraryService.library.repository.UserRepository;
import MJLee.LibraryService.library.service.user.GetUserService;
import MJLee.LibraryService.library.service.user.ModifyUserService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RentServiceTest {

    @Autowired
    BookRepository repository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    GetUserService getUserService;
    @Autowired
    ModifyUserService modifyUserService;

    @Transactional
    @Test
    void TestA() {
        Book book = new Book();
        book.setName("bookname1");
        book.setAuthor("author1");
        repository.save(book);

        User user = new User();
        user.setUserName("user1");
        user.setNickName("nickname1");
        user.setCreateUserTime(new Date());

        if(userRepository.findByNickName("nickname1").isEmpty()) userRepository.save(user);

        UserDto userDto = new UserDto();
        userDto.setName("user1");
        userDto.setNickName("nickname1");

        Book bookFound = repository.findAll().stream()
                .filter(b -> b.getAuthor().equals("author1"))
                .filter(b -> b.getName().equals("bookname1"))
                .findFirst()
                .orElse(null);


        if (bookFound == null) {
            System.out.println("fNull");
            return;
        }

        if (bookFound.isRent()) {
            System.out.println("f");
            return;
        }

        if(!getUserService.userCanRent(userDto)) {
            System.out.println("fRent");
            return;
        }

        bookFound.setRent(true);
        Date date = new Date();
        bookFound.setStartRent(date);
        bookFound.setDeadlineRent(Date.from(date.toInstant().plus(Duration.ofDays(14))));
        bookFound.setUser(user);

        user.setRentedBooks(user.getRentedBooks()+1);
        modifyUserService.modifyUser(userDto);

        repository.save(bookFound);

        System.out.println("t");
    }

//    @Test
//    void TestB() {
//        UserDto userDto = new UserDto();
//        userDto.setName("user1");
//        userDto.setNickName("nickname1");
//
//        Book bookFound = repository.findAll().stream()
//                .filter(b -> b.getAuthor().equals("author1"))
//                .filter(b -> b.getName().equals("bookname1"))
//                .findFirst()
//                .orElse(null);
//        System.out.println(bookFound);
//        if(bookFound == null){
//            System.out.println("null");
//            return;
//        }
//
//        if (!bookFound.isRent()) {
//            System.out.println("rent");
//            return;
//        }
//
//
//        if(getUserService.deadLineOfUser(userDto).compareTo(new Date()) > 0){
//            bookFound.getUser().setCanRent(false);
//            modifyUserService.modifyUser(userDto);
//        }
//
//        bookFound.setRent(false);
//        repository.save(bookFound);
//
//        System.out.println("return");
//    }
}