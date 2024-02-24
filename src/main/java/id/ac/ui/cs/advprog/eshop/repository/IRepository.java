package id.ac.ui.cs.advprog.eshop.repository;

import java.util.Iterator;

public interface IRepository<T> {
    T create(T product);

    T findById(String id);

    T edit(String id, T updatedProduct);

    Iterator<T> findAll();

    void deleteById(String id);
}