package pl.pg.aui.bookshelf;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.lang.NonNull;
import pl.pg.aui.book.Book;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static javax.persistence.FetchType.LAZY;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
@Entity
@Table(name = "bookshelves")
public class Bookshelf implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String category;

    @OneToMany(fetch = LAZY, mappedBy = "bookshelf", cascade = CascadeType.ALL)
    private List<Book> books;

    public void addBook(Book book) {
        if(Objects.isNull(books)) {
            books = new ArrayList<Book>(List.of(book));
        }
        books.add(book);
    }
}
