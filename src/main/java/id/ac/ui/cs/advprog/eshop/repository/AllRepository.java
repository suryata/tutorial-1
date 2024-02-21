package id.ac.ui.cs.advprog.eshop.repository;

import java.util.Iterator;

public interface AllRepository<T> {
    T create(T object);
    Iterator<T> findAll();
    T findById(String id);
    T update(String id, T object);
    void delete(String id);
}
