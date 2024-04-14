package com.library.app.db_utils;

import java.util.List;


public interface Dao <T> {
    List<T> findAll();
}
