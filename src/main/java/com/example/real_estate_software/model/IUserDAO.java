package com.example.real_estate_software.model;

import java.util.List;

public interface IUserDAO<T> {
    void createTable();
    void insertNew(T entity);
    void delete(T entity);
    void update(T entity);
    List<T> get(T entity);
}
