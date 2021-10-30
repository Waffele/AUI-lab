package pl.pg.aui.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import pl.pg.aui.book.rest.BookAlreadyExistsException;
import pl.pg.aui.book.rest.BookNotFoundException;
import pl.pg.aui.bookshelf.Bookshelf;
import pl.pg.aui.bookshelf.BookshelfService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private BookRepository bookRepository;
    private BookshelfService bookshelfService;

    @Autowired
    public BookService(BookRepository bookRepository, BookshelfService bookshelfService) {
        this.bookRepository = bookRepository;
        this.bookshelfService = bookshelfService;
    }

    public Optional<Book> findBook(String ISBN) {
        return bookRepository.findByISBN(ISBN);
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    @Transactional
    public Book addBook(Book book, String bookshelfCategory) {
        Bookshelf bookshelf = bookshelfService.findBookshelf(bookshelfCategory)
                .orElseGet(
                        () -> bookshelfService.addBookshelf(Bookshelf.builder()
                                .category(bookshelfCategory)
                                .build()));
        book.setBookshelf(bookshelf);
        try {
            return bookRepository.save(book);
        } catch (DataIntegrityViolationException e) {
            throw new BookAlreadyExistsException();
        }
    }

    @Transactional
    public Book updateBook(Book book, String bookshelfCategory) {
        Book bookInRepo = findBook(book.getISBN()).orElseThrow(BookNotFoundException::new);
        Bookshelf bookshelf = bookshelfService.findBookshelf(bookshelfCategory)
                .orElseGet(
                        () -> bookshelfService.addBookshelf(Bookshelf.builder()
                                .category(bookshelfCategory)
                                .build()));
        if(bookInRepo.getBookshelf().getBooks().size() == 1) {
            bookshelfService.deleteBookshelf(bookInRepo.getBookshelf().getId());
        }
        book.setId(bookInRepo.getId());
        book.setBookshelf(bookshelf);
        return bookRepository.save(book);
    }

    public void deleteBook(String ISBN) {
        findBook(ISBN).ifPresentOrElse(it -> bookRepository.delete(it),
                () -> {
                    throw new BookNotFoundException();
                }
        );
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

}
