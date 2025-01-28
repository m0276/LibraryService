package MJLee.LibraryService.library.service.book;

import MJLee.LibraryService.library.dto.BookDto;
import MJLee.LibraryService.library.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Transactional
@Service
public class DeleteService {
    BookRepository repository;

    @Autowired
    public DeleteService(BookRepository repository){
        this.repository = repository;
    }

    public boolean deleteBook(BookDto book){
        if(repository.findAllByName(book.getName()).stream()
                .filter(b -> b.getAuthor().equals(book.getAuthor()))
                .findFirst().isEmpty()) return false;
        else{
            repository.delete(repository.findAllByName(book.getName()).stream()
                    .filter(b -> b.getAuthor().equals(book.getAuthor()))
                    .findFirst().get());
            return true;
        }
    }
}
