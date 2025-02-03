package MJLee.LibraryService.library.service.book;

import MJLee.LibraryService.library.entity.Book;
import MJLee.LibraryService.library.entity.User;
import MJLee.LibraryService.library.repository.BookRepository;
import MJLee.LibraryService.library.service.user.GetUserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class ShowService {
    BookRepository repository;
    GetUserService getUserService;

    @Autowired
    public ShowService(BookRepository repository, GetUserService getUserService) {
        this.repository = repository;
        this.getUserService = getUserService;
    }

    public List<Book> showAllBook(){
        return repository.findAll();
    }

    public List<Book> showBooksByAuthor(String author) {
        return repository.findByAuthor(author);
    }

    public List<Book> showBooksByName(String name) {
        return repository.findByName(name);
    }

    public Book showBookNameAndAuthor(String name, String author){
        return repository.findByAuthor(author).stream().filter(b -> b.getName().equals(name)).findFirst().get();
    }

    public boolean showBookNameAndAuthorRent(String name, String author){
        return repository.findByAuthor(author).stream().filter(b -> b.getName().equals(name)).findFirst().get().isRent();
    }

    public List<Book> showUserRent(String nickName){
        Optional<User> userOptional = getUserService.findByNickName(nickName);
        if (userOptional.isEmpty()) {
            return null;
        }
        User user = userOptional.get();
        return repository.findByUser(user);
    }
}
