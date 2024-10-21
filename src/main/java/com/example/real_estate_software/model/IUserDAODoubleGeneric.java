package com.example.real_estate_software.model;

import java.util.List;

public interface IUserDAODoubleGeneric<T,U> {
    void createTable();
    void insertNew(T entity, U additionalentity);
    void delete(T entity);
    void update(T entity, U additonalentity);
    List<T> getAll();
    T getAllBool(boolean bool);
}
