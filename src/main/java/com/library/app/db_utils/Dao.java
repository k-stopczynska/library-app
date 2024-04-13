package com.library.app.db_utils;

import com.library.app.model.Book;

import java.util.List;

public interface Dao {
    List<Book> finAll();
    Book findOne(long Id);
    void add(Book book);
    void update(Book book);
    void delete(long Id);
}
