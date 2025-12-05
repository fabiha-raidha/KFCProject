package class_controller;

import java.util.List;

public interface Repository<T> {
    void add(T item);
    void update(T item);
    void delete(T item);
    List<T> getAll();
    void saveAll();
}