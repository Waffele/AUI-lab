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

import java.util.ArrayList;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
public class CreateBookshelfRequest {
    private String category;
    private int height;

    public Bookshelf toBookShelf() {
        return Bookshelf.builder()
                .category(category)
                .height(height)
                .books(new ArrayList<>())
                .id(0L)
                .build();
    }
}
