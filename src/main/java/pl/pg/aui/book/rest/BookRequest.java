package pl.pg.aui.book.rest;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import pl.pg.aui.book.Book;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
public class BookRequest {
    private String title;
    private int pagesCount;
    private String ISBN;
    private String bookshelfCategory;

    public Book toBook(){
        return Book.builder()
                .title(title)
                .pagesCount(pagesCount)
                .ISBN(ISBN)
                .build();
    }
}
