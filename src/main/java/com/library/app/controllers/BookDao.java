package com.library.app.controllers;

import com.library.app.Constants;
import com.library.app.db_utils.Dao;
import com.library.app.db_utils.DbConnection;
import com.library.app.model.Book;

import java.sql.*;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Logger;


public class BookDao extends DbConnection implements Dao <Book> {

    Logger LOGGER = Logger.getLogger("");

    @Override
    public List<Book> findAll() {
        List<Book> books = new ArrayList<>(Collections.emptyList());
        String query = String.format("SELECT * FROM %s", Constants.DB_TABLE.getValue());
        try (
                Connection connection = getConnection();
                Statement stmt = connection.createStatement();
                ResultSet rst = stmt.executeQuery(query)
        ) {
            while (rst.next()) {
                Book book = new Book();
                book.setId(rst.getLong("id"));
                book.setTitle(rst.getNString("title"));
                books.add(book);
            }

        } catch (SQLException e) {
            LOGGER.throwing("BookDao", "findAll", e);
        }
        return books;
    }

    @Override
    public Optional<Book> findByTitle(String title) {
        String query = String.format("SELECT id, title FROM %s WHERE title = ?", Constants.DB_TABLE.getValue());
        Optional<Book> book = Optional.empty();
        try (
                Connection connection = getConnection();
                PreparedStatement prepStmt = connection.prepareStatement(query)
        ) {
            prepStmt.setString(1, title);
            try (
                    ResultSet rset = prepStmt.executeQuery()) {
                Book resBook = new Book();
                if (rset.next()) {
                    resBook.setId(rset.getLong("id"));
                    resBook.setTitle(rset.getNString("title"));
                }
                book = Optional.of(resBook);

            } catch (SQLException e) {
                LOGGER.throwing("BookDao", "findByTitle", e);
            }
        } catch (SQLException e) {
            LOGGER.throwing("BookDao", "findByTitle", e);
        }
        return book;
    }

    @Override
    public Optional<Book> findById(long id) {
        String query = String.format("SELECT id, title FROM %s WHERE id = ?", Constants.DB_TABLE.getValue());
        Optional<Book> book = Optional.empty();
        try (
                Connection connection = getConnection();
                PreparedStatement prepStmt = connection.prepareStatement(query)
        ) {
            prepStmt.setLong(1, id);
            try (
                    ResultSet rset = prepStmt.executeQuery()) {
                Book resBook = new Book();
                if (rset.next()) {
                    resBook.setId(rset.getLong("id"));
                    resBook.setTitle(rset.getNString("title"));
                }
                book = Optional.of(resBook);

            } catch (SQLException e) {
                LOGGER.throwing("BookDao", "findById", e);
            }
        } catch (SQLException e) {
            LOGGER.throwing("BookDao", "findById", e);
        }
        return book;
    }

    @Override
    public void delete(Book book) {
        String query = String.format("DELETE FROM %s WHERE id = ?", Constants.DB_TABLE.getValue());
        try (
                Connection connection = getConnection();
                PreparedStatement prepStmt = connection.prepareStatement(query)
        ) {
            prepStmt.setLong(1, book.getId());
            prepStmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.throwing("BookDao", "delete", e);
        }
    }

    @Override
    public Book create(Book book) {
        String query = String.format("INSERT INTO %s (title) VALUES (?)", Constants.DB_TABLE.getValue());
        try (
                Connection connection = getConnection();
                PreparedStatement prepStmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)
        ) {
            prepStmt.setString(1, book.getTitle());
            prepStmt.executeUpdate();
            try (
                    ResultSet rset = prepStmt.getGeneratedKeys()) {
                if (rset.next()) {
                    book.setId(rset.getLong(1));
                }
            } catch (SQLException e) {
                LOGGER.throwing("BookDao", "findById", e);
            }
        } catch (SQLException e) {
            LOGGER.throwing("BookDao", "delete", e);
        }
        return book;
    }
}
