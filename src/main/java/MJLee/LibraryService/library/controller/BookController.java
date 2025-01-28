package MJLee.LibraryService.library.controller;


import MJLee.LibraryService.library.dto.BookDto;
import MJLee.LibraryService.library.dto.UserDto;
import MJLee.LibraryService.library.entity.Book;
import MJLee.LibraryService.library.service.book.CreateService;
import MJLee.LibraryService.library.service.book.DeleteService;
import MJLee.LibraryService.library.service.book.RentService;
import MJLee.LibraryService.library.service.book.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/library/books")
public class BookController {

    CreateService createservice;
    DeleteService deleteService;
    RentService rentService;
    ShowService showService;

    @Autowired
    public BookController(CreateService createservice, DeleteService deleteService, RentService rentService, ShowService showService){
        this.createservice = createservice;
        this.deleteService = deleteService;
        this.rentService = rentService;
        this.showService = showService;
    }

    @GetMapping("/{name}/{author}")
    public ModelAndView getBooksByNameAndAuthor(@PathVariable String name, @PathVariable String author ,Model model) {
        Book books = showService.showBookNameAndAuthor(name, author);
        model.addAttribute("books", books);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("book-list");
        return modelAndView;
    }

    @GetMapping("/author/{author}")
    public ModelAndView getBooksByAuthor(@PathVariable String author, Model model) {
        List<Book> books = showService.showBooksByAuthor(author);
        model.addAttribute("books", books);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("book-list");
        return modelAndView;
    }

    @GetMapping("/name/{name}")
    public ModelAndView getBooksByName(@PathVariable String name, Model model) {
        List<Book> books = showService.showBooksByName(name);
        model.addAttribute("books", books);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("book-list");
        return modelAndView;
    }

    @GetMapping
    public ModelAndView getBooks(Model model) {
        List<Book> books = showService.showAllBook();
        model.addAttribute("books", books);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("book-list");
        return modelAndView;
    }

    @GetMapping("/{userNickName}")
    public ModelAndView getUserRentedBooks(Model model, @PathVariable String userNickName){
        List<Book> books = showService.showUserRent(userNickName);
        model.addAttribute("books", books);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("book-list");
        return modelAndView;
    }

    @PostMapping
    public ResponseEntity<Void> createBook(@RequestBody BookDto bookDto) {
        createservice.saveBook(bookDto.getName(), bookDto.getAuthor());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/status/1")
    public ResponseEntity<Void> rentStatus(@RequestBody BookDto bookDto, @RequestBody UserDto userDto){
        if (rentService.rent(bookDto,userDto)) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/status/0")
    public ResponseEntity<Void> rentReturn(@RequestBody BookDto bookDto, @RequestBody UserDto userDto){
        if (rentService.returned(bookDto, userDto)) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/{name}/{author}")
    public ResponseEntity<Void> delete(@PathVariable String name, @PathVariable String author){
        BookDto bookDto = new BookDto();
        bookDto.setName(name);
        bookDto.setAuthor(author);

        if(showService.showBookNameAndAuthorRent(name,author)) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        else {
            if(deleteService.deleteBook(bookDto)) return ResponseEntity.status(HttpStatus.OK).build();
            else return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

}