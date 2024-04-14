package com.library.app.db_utils;

import com.library.app.Constants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

import io.github.cdimascio.dotenv.Dotenv;

public class DbConnection {

    Logger LOGGER = Logger.getLogger("");

    protected Connection getConnection() throws SQLException {
        Dotenv dotenv = Dotenv.configure()
                .ignoreIfMalformed()
                .ignoreIfMissing()
                .load();

        String url = Constants.DB_URL.getValue();
        String user = dotenv.get("DB_USER");
        String password = dotenv.get("DB_PASSWORD");

        Connection connection = DriverManager.getConnection(url, user, password);
        LOGGER.info(String.format("Connected to database %s", url));

        return connection;
    }
}
