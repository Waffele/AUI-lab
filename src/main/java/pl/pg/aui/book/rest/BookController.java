package pl.pg.aui.book.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pg.aui.book.BookService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/books")
public class BookController {
    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookResponse> getBooks() {
        return bookService.findAll().stream()
                .map(BookResponse::fromBook)
                .collect(Collectors.toList());
    }

    @GetMapping("{ISBN}")
    public BookResponse getBook(@PathVariable String ISBN) {
        return BookResponse.fromBook(
                bookService.findBook(ISBN).orElseThrow(BookNotFoundException::new)
        );
    }

    @PutMapping
    public BookResponse updateBook(@RequestBody BookRequest request) {
        return BookResponse.fromBook(
                bookService.updateBook(request.toBook(), request.getBookshelfCategory())
        );
    }

    @PostMapping
    public BookResponse addBook(@RequestBody BookRequest request) {
        return BookResponse.fromBook(
                bookService.addBook(request.toBook(), request.getBookshelfCategory())
        );
    }

    @DeleteMapping("{ISBN}")
    public void deleteBook(@PathVariable String ISBN) {
        bookService.deleteBook(ISBN);
    }
}
