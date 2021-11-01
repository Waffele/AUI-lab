package pl.pg.aui.bookshelf.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.pg.aui.bookshelf.BookshelfService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/bookshelves")
public class BookshelfController {
    private BookshelfService bookshelfService;

    @Autowired
    public BookshelfController(BookshelfService bookshelfService) {
        this.bookshelfService = bookshelfService;
    }

    @PostMapping
    public void addBookshelf(@RequestBody CreateBookshelf request) {
        bookshelfService.addBookshelf(request.toBookShelf());
    }

    @DeleteMapping("{category}")
    public void deleteBookshelf(@PathVariable String category) {
        bookshelfService.deleteBookshelf(category);
    }
}
