package pl.pg.aui.bookshelf.rest;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
public class UpdateBookshelf {
    private String oldCategory;
    private String newCategory;
    private int height;
}
