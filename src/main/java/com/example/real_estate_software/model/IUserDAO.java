package com.example.real_estate_software.model;

import java.util.List;
//Generic class for any user based DAOs
public interface IUserDAO<T> {
    void createTable();
    void insertNew(T entity); //need to be able to declare multiple generic types
    void delete(T entity);
    void update(T entity);
    List<T> getAll(T entity);
}
