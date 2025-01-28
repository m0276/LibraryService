package MJLee.LibraryService.library.repository;

import MJLee.LibraryService.library.dto.BookDto;
import MJLee.LibraryService.library.dto.UserDto;
import MJLee.LibraryService.library.entity.Book;
import MJLee.LibraryService.library.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAll();
    List<Book> findByAuthor(String author);
    List<Book> findByName(String name);
    List<Book> findAllByName(String name);
    List<Book> findByUser(User user);
}
