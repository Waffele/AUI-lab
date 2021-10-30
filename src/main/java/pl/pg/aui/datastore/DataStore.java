package pl.pg.aui.datastore;

import lombok.extern.java.Log;
import org.springframework.stereotype.Component;
import pl.pg.aui.bookshelf.Bookshelf;
import pl.pg.aui.serialization.CloningUtility;
import pl.pg.aui.book.Book;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Log
@Component
public class DataStore {

    private Set<Book> books = new HashSet<>();
    private Set<Bookshelf> bookshelves = new HashSet<>();

    public synchronized List<Book> findAllBooks() {
        return new ArrayList<>(books);
    }

    public synchronized Optional<pl.pg.aui.book.Book> findBook(String ISBN) {
        return books.stream()
                .filter(it -> it.getISBN().equals(ISBN))
                .findFirst()
                .map(CloningUtility::clone);
    }

    public synchronized void createBook(Book book) throws IllegalArgumentException {
        findBook(book.getISBN()).ifPresentOrElse(
                original -> {
                    throw new IllegalArgumentException(
                            String.format("The profession name \"%s\" is not unique", book.getISBN()));
                },
                () -> books.add(CloningUtility.clone(book)));
    }

    public synchronized List<Bookshelf> findAllBookshelves() {
        return bookshelves.stream()
                .map(CloningUtility::clone)
                .collect(Collectors.toList());
    }

    public synchronized Optional<Bookshelf> findBookshelf(Long id) {
        return bookshelves.stream()
                .filter(character -> character.getId().equals(id))
                .findFirst()
                .map(CloningUtility::clone);
    }

    public synchronized void createBookshelf(Bookshelf bookshelf) throws IllegalArgumentException {
        bookshelf.setId(findAllBookshelves().stream()
                .mapToLong(Bookshelf::getId)
                .max().orElse(0) + 1);
        bookshelves.add(CloningUtility.clone(bookshelf));
    }

    public synchronized void updateBookshelf(Bookshelf bookshelf) throws IllegalArgumentException {
        findBookshelf(bookshelf.getId()).ifPresentOrElse(
                original -> {
                    bookshelves.remove(original);
                    bookshelves.add(CloningUtility.clone(bookshelf));
                },
                () -> {
                    throw new IllegalArgumentException(
                            String.format("The character with id \"%d\" does not exist", bookshelf.getId()));
                });
    }

    public synchronized void deleteBookshelf(Long id) throws IllegalArgumentException {
        findBookshelf(id).ifPresentOrElse(
                original -> bookshelves.remove(original),
                () -> {
                    throw new IllegalArgumentException(
                            String.format("The character with id \"%d\" does not exist", id));
                });
    }

    public synchronized void deleteBook(String ISBN) throws IllegalArgumentException {
        findBook(ISBN).ifPresentOrElse(
                original -> bookshelves.remove(original),
                () -> {
                    throw new IllegalArgumentException(
                            String.format("The character with id \"%d\" does not exist", ISBN));
                });
    }

    public void updateBook(Book book) {
        findBook(book.getISBN()).ifPresentOrElse(
                original -> {
                    books.remove(original);
                    books.add(CloningUtility.clone(book));
                },
                () -> {
                    throw new IllegalArgumentException(
                            String.format("The character with id \"%d\" does not exist", book.getISBN()));
                });
    }
}
