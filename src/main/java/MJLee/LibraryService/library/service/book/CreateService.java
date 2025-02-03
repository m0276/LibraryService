package MJLee.LibraryService.library.service.book;

import MJLee.LibraryService.library.entity.Book;
import MJLee.LibraryService.library.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class CreateService {

    BookRepository repository;

    @Autowired
    public CreateService(BookRepository repository){
        this.repository = repository;
    }

    public void saveBook(String name, String author) {
        Book book = new Book();
        book.setName(name);
        book.setAuthor(author);
        book.setRent(false);

        repository.save(book);
    }
}
