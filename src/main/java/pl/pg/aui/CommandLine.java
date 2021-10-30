package pl.pg.aui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import pl.pg.aui.book.Book;
import pl.pg.aui.book.BookService;
import pl.pg.aui.bookshelf.Bookshelf;
import pl.pg.aui.bookshelf.BookshelfService;

import javax.annotation.PostConstruct;
import java.util.Scanner;

@Component
public class CommandLine implements CommandLineRunner {
    private ApplicationContext appContext;
    private BookService bookService;
    private BookshelfService bookshelfService;

    @Autowired
    public CommandLine(ApplicationContext appContext, BookService bookService, BookshelfService bookshelfService) {
        this.appContext = appContext;
        this.bookService = bookService;
        this.bookshelfService = bookshelfService;
    }

    @PostConstruct
    public void initLibrary() {
        bookshelfService.addBookshelf(new Bookshelf(1L, "1"));
        bookshelfService.addBookshelf(new Bookshelf(2L, "2"));
        bookService.addBook(new Book("title1", 100, "1", 1L));
        bookService.addBook(new Book("title12", 102, "12", 1L));
        bookService.addBook(new Book("title21", 103, "21", 2L));
        bookService.addBook(new Book("title22", 104, "22", 2L));
        bookService.addBook(new Book("title13", 105, "13", 1L));
    }

    @Override
    public void run(String... args) throws Exception {
        CLIloop();
    }

    private void CLIloop() {
        Scanner command = new Scanner(System.in);
        String text = "";
        String[] inputs;

        while (!text.equals("exit")) {
            System.out.println("Enter command: ");
            text = command.nextLine();
            inputs = text.split(" ");

            switch (inputs[0]) {
                case "all":
                    all();
                    break;
                case "add":
                    if (inputs[1].equals("book")) {
                        bookService.addBook(
                                new Book(inputs[2], Integer.parseInt(inputs[3]), inputs[4], Long.parseLong(inputs[5]))
                        );
                    } else if (inputs[1].equals("bookshelf")) {
                        bookshelfService.addBookshelf(new Bookshelf(0L, inputs[2]));
                    }
                    break;
                case "delete":
                    if (inputs[1].equals("book")) {
                        bookService.deleteBook(inputs[2]);
                    } else if (inputs[1].equals("bookshelf")) {
                        bookshelfService.deleteBook(Long.parseLong(inputs[2]));
                    }
                    break;
                case "exit":
                    stopApp();
                    break;
                default:
                    System.out.println("Command not recognized!");
                    break;
            }
        }
    }

    private void stopApp() {
        SpringApplication.exit(appContext, () -> 0);
    }

    private void all() {
        bookshelfService.findAll().forEach(System.out::println);
        bookService.findAll().forEach(System.out::println);
    }

}

