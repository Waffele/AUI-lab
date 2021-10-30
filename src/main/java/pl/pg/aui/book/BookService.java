package pl.pg.aui.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Optional<Book> findBook(String ISBN) {
        return bookRepository.find(ISBN);
    }

    public void addBook(Book book) {
        bookRepository.create(book);
    }

    public void deleteBook(String ISBN) {
        findBook(ISBN).ifPresent(it -> bookRepository.delete(it));
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

}
