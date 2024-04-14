package com.library.app.controllers;

import com.library.app.Constants;
import com.library.app.db_utils.Dao;
import com.library.app.db_utils.DbConnection;
import com.library.app.model.Book;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Logger;

import static com.library.app.Constants.DB_TABLE;

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
            System.out.println("BookDao in try");
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
}
