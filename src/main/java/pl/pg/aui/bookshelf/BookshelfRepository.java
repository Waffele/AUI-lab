package pl.pg.aui.bookshelf;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@org.springframework.stereotype.Repository
public interface BookshelfRepository extends JpaRepository<Bookshelf, Long> {
    Optional<Bookshelf> findByCategory(String category);
    void deleteByCategory(String category);
}
