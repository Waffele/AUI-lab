package pl.pg.aui.bookshelf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pg.aui.bookshelf.rest.BookshelfAlreadyExistsException;
import pl.pg.aui.bookshelf.rest.BookshelfNotFoundException;
import pl.pg.aui.bookshelf.rest.UpdateBookshelf;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BookshelfService {

    private BookshelfRepository bookshelfRepository;

    @Autowired
    public BookshelfService(BookshelfRepository bookshelfRepository) {
        this.bookshelfRepository = bookshelfRepository;
    }

    public Optional<Bookshelf> findBookshelf(Long id) {
        return bookshelfRepository.findById(id);
    }

    public Optional<Bookshelf> findBookshelf(String category) {
        return bookshelfRepository.findByCategory(category);
    }

    public Bookshelf addBookshelf(Bookshelf bookshelf) {
        return bookshelfRepository.save(bookshelf);
    }

    public void deleteBookshelf(Long id) {
        findBookshelf(id).ifPresent(it -> bookshelfRepository.delete(it));
    }

    @Transactional
    public void deleteBookshelf(String category) {
        bookshelfRepository.deleteByCategory(category);
    }

    public List<Bookshelf> findAll() {
        return bookshelfRepository.findAll();
    }

    public Bookshelf updateBookshelf(UpdateBookshelf request) {
        findBookshelf(request.getNewCategory()).ifPresent(
                it -> {
                    throw new BookshelfAlreadyExistsException();
                }
        );
        Bookshelf bookshelf = findBookshelf(request.getOldCategory()).orElseThrow(BookshelfNotFoundException::new);
        bookshelf.setCategory(request.getNewCategory());
        bookshelf.setHeight(request.getHeight());
        return bookshelfRepository.save(bookshelf);
    }
}
