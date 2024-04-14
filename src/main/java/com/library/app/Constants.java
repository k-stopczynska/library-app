package com.library.app;

public enum Constants {
    DB_URL ("jdbc:mysql://localhost:3306/library_db"),
    DB_TABLE ("books");

    private final String value;

    Constants(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
