package pl.pg.aui.bookshelf;

import lombok.*;
import lombok.experimental.SuperBuilder;
import pl.pg.aui.book.Book;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

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
    private int height;

    @OneToMany(mappedBy="bookshelf", cascade = CascadeType.ALL)
    private List<Book> books;
}
