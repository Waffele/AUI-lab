package pl.pg.aui.bookshelf.rest;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Bookshelf already exists")
public class BookshelfAlreadyExistsException extends RuntimeException {
}
