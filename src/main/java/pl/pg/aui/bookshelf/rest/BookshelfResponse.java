package pl.pg.aui.bookshelf.rest;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import pl.pg.aui.book.Book;
import pl.pg.aui.bookshelf.Bookshelf;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
public class BookshelfResponse {

    private String category;
    private List<Book> books;

    public static BookshelfResponse fromBookshelf(Bookshelf bookshelf) {
        return BookshelfResponse.builder()
                .books(bookshelf.getBooks())
                .category(bookshelf.getCategory())
                .build();
    }
}
