package com.library.app.db_utils;

import java.util.List;
import java.util.Optional;


public interface Dao <T> {
    List<T> findAll();
    Optional<T> findByTitle(String title);
    Optional<T> findById(long id);
    void delete(T t);
}
