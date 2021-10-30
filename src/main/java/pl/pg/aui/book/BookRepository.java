package pl.pg.aui.book;


import org.springframework.beans.factory.annotation.Autowired;
import pl.pg.aui.datastore.DataStore;
import pl.pg.aui.repository.Repository;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public class BookRepository implements Repository<Book, String> {

    private DataStore dataStore;

    @Autowired
    public BookRepository(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    @Override
    public Optional<Book> find(String ISBN) {
        return dataStore.findBook(ISBN);
    }

    @Override
    public List<Book> findAll() {
        return dataStore.findAllBooks();
    }

    @Override
    public void create(Book book) {
        dataStore.createBook(book);
    }

    @Override
    public void delete(Book book) {
        dataStore.deleteBook(book.getISBN());
    }

    @Override
    public void update(Book book) {
        dataStore.updateBook(book);
    }
}
