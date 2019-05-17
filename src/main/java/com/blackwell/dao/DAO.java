package com.blackwell.dao;

import java.util.List;

public interface DAO<T> {
    List<T> get();
    T get(int id);
    void save(T t);
    void delete(int id);
}
