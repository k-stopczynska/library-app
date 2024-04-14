package com.library.app;

import com.library.app.controllers.BookDao;
import com.library.app.db_utils.Dao;
import com.library.app.model.Book;

import java.util.List;

public class App
{
    public static void main( String[] args )
    {
        Dao<Book> bookDao = new BookDao();
        List<Book> books = bookDao.findAll();
        for (Book book: books) {
            System.out.println(book.getTitle());
        }
    }
}
