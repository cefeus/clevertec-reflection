package dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {

    Optional<T> get(T t);

    List<T> getAll();

    void save(T t);

    void delete(T t);
}
