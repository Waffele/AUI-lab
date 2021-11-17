package pl.pg.aui.bookshelf.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pg.aui.book.Book;
import pl.pg.aui.book.BookService;
import pl.pg.aui.book.rest.BookNotFoundException;
import pl.pg.aui.book.rest.BookRequest;
import pl.pg.aui.book.rest.BookResponse;
import pl.pg.aui.bookshelf.BookshelfService;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("api/bookshelves")
public class BookshelfController {
    private BookshelfService bookshelfService;
    private BookService bookService;

    @Autowired
    public BookshelfController(BookshelfService bookshelfService, BookService bookService) {
        this.bookshelfService = bookshelfService;
        this.bookService = bookService;
    }

    @PostMapping
    public void addBookshelf(@RequestBody CreateBookshelf request) {
        bookshelfService.addBookshelf(request.toBookShelf());
    }

    @DeleteMapping("{category}")
    public void deleteBookshelf(@PathVariable String category) {
        bookshelfService.deleteBookshelf(category);
    }

    @GetMapping("{category}/books/{ISBN}")
    public BookResponse getBookOfBookshelf(@PathVariable String category, @PathVariable String ISBN) {
        return bookService.findBookOfBookshelf(ISBN, category);

    }

    @PostMapping("{category}/books/{ISBN}")
    public void addBookOfBookshelf(@PathVariable String category, @PathVariable String ISBN, @RequestBody BookRequest request) {
        bookService.addBook(request.toBook(), category);
    }

    @PutMapping("{category}/books/{ISBN}")
    public void updateBookOfBookshelf(@PathVariable String category, @PathVariable String ISBN, @RequestBody BookRequest request) {
        bookService.updateBook(request.toBook(), request.getBookshelfCategory(), ISBN );
    }

    @DeleteMapping("{category}/books/{ISBN}")
    public void deleteBookOfBookshelf(@PathVariable String category, @PathVariable String ISBN) {
        bookService.deleteBook(ISBN);
    }

    @GetMapping("{category}/books")
    public List<Book> getBooksOfBookshelf(@PathVariable String category) {
        return bookshelfService.findBookshelf(category)
                .orElseThrow(BookshelfNotFoundException::new)
                .getBooks();
    }
}
