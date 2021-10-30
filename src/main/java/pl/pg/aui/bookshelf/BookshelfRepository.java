package pl.pg.aui.bookshelf;

import org.springframework.beans.factory.annotation.Autowired;
import pl.pg.aui.datastore.DataStore;
import pl.pg.aui.repository.Repository;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public class BookshelfRepository implements Repository<Bookshelf, Long> {

    private DataStore dataStore;

    @Autowired
    public BookshelfRepository(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    @Override
    public Optional<Bookshelf> find(Long id) {
        return dataStore.findBookshelf(id);
    }

    @Override
    public List<Bookshelf> findAll() {
        return dataStore.findAllBookshelves();
    }

    @Override
    public void create(Bookshelf entity) {
        dataStore.createBookshelf(entity);
    }

    @Override
    public void delete(Bookshelf entity) {
        dataStore.deleteBookshelf(entity.getId());
    }

    @Override
    public void update(Bookshelf entity) {
        dataStore.updateBookshelf(entity);
    }
}
