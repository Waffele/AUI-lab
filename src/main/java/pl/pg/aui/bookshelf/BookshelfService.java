package pl.pg.aui.bookshelf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pg.aui.book.BookService;

import java.util.List;
import java.util.Optional;

@Service
public class BookshelfService {

    private BookshelfRepository bookshelfRepository;
    private BookService bookService;

    @Autowired
    public BookshelfService(BookshelfRepository bookshelfRepository, BookService bookService) {
        this.bookshelfRepository = bookshelfRepository;
        this.bookService = bookService;
    }

    public Optional<Bookshelf> findBookshelf(Long id) {
        return bookshelfRepository.find(id);
    }

    public void addBookshelf(Bookshelf bookshelf) {
        bookshelfRepository.create(bookshelf);
    }

    public void deleteBook(Long id) {
        Optional<Bookshelf> bookshelf = findBookshelf(id);
        if (bookshelf.isEmpty()) {
            return;
        }
        Bookshelf bookshelf1 = bookshelf.get();
        if (bookService.findAll().stream().anyMatch(it -> it.getBookshelfId().equals(id))) {
            return;
        }
        bookshelfRepository.delete(bookshelf1);
    }

    public List<Bookshelf> findAll() {
        return bookshelfRepository.findAll();
    }

}
