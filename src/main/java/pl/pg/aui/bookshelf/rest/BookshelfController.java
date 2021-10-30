package pl.pg.aui.bookshelf.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @GetMapping
    public List<BookshelfResponse> getBookshelves() {
        return bookshelfService.findAll().stream()
                .map(BookshelfResponse::fromBookshelf)
                .collect(Collectors.toList());
    }

    @GetMapping("{category}")
    public BookshelfResponse getBookshelf(@PathVariable String category) {
        return BookshelfResponse.fromBookshelf(
                bookshelfService.findBookshelf(category)
                        .orElseThrow(BookshelfNotFoundException::new)
        );
    }

    @PostMapping
    public BookshelfResponse addBookshelf(@RequestBody CreateBookshelfRequest request) {
        return BookshelfResponse.fromBookshelf(
                bookshelfService.addBookshelf(request.toBookShelf())
        );
    }

    @PutMapping
    public BookshelfResponse updateBookshelf(@RequestBody RenameBookshelfRequest request) {
        return BookshelfResponse.fromBookshelf(
                bookshelfService.updateBookshelf(request.getOldCategory(), request.getNewCategory())
        );
    }

    @DeleteMapping("{category}")
    public void deleteBookshelf(@PathVariable String category) {
        bookshelfService.deleteBookshelf(category);
    }
}
