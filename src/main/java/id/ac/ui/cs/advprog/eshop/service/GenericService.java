package id.ac.ui.cs.advprog.eshop.service;

import java.util.List;

public interface GenericService<T> {
    T create(T entity);
    List<T> findAll();
    T update(String id, T entity);
    T findById(String id);
    void delete(String id);
}
