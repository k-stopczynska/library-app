package com.library.app;

import com.library.app.controllers.BookDao;
import com.library.app.db_utils.Dao;
import com.library.app.model.Book;

import java.util.Optional;
import java.util.List;

public class App
{
    public static void main( String[] args ) {
        Dao<Book> bookDao = new BookDao();
//        List<Book> books = bookDao.findAll();
//        for (Book book: books) {
//            System.out.println(book.getTitle());
//        }
//
//        Optional<Book> optBook = bookDao.findByTitle("The Catcher in the Rye");
//        if (optBook.isPresent()) {
//            Book book = optBook.get();
//            System.out.println("id: " + book.getId());
//            System.out.println("title: " + book.getTitle());
//        }
//
//        Optional<Book> optBook2 = bookDao.findById(7);
//        if (optBook2.isPresent()) {
//            Book book = optBook2.get();
//            System.out.println("id: " + book.getId());
//            System.out.println("title: " + book.getTitle());
//        }
//
//        Optional<Book> optBook3 = bookDao.findById(3);
//        if (optBook3.isPresent()) {
//            Book book = optBook3.get();
//            bookDao.delete(book);
//        }

//        Book newBook = new Book();
//        newBook.setTitle("some new book");
//        Book copy = bookDao.create(newBook);
//        System.out.println(copy.getTitle());
//        System.out.println(copy.getId());

        Book newBook = new Book();
        newBook.setId(10);
        newBook.setTitle("new Alchemist");
        Book copy = bookDao.update(newBook);
        System.out.println(copy.getTitle());
        System.out.println(copy.getId());
    }
}
