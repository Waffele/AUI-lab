package pl.pg.aui.book;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@org.springframework.stereotype.Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    public Optional<Book> findByISBN(String ISBN);
}