package pl.pg.aui.bookshelf.rest;

import lombok.*;
import lombok.experimental.SuperBuilder;
import pl.pg.aui.bookshelf.Bookshelf;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
public class CreateBookshelf {
    private String category;
    private int height;

    public Bookshelf toBookShelf() {
        return Bookshelf.builder()
                .category(category)
                .height(height)
                .id(0L)
                .build();
    }
}
