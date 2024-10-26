package com.example.real_estate_software.model;

import java.util.List;

/**
 * Interface for any DAOs of Object type User, this interface only takes a single generic type specification
 *
 * @param <T> The type of entity that this class will handle (Owner, Tenant, Property etc.). This entity must remain consistent for the entire application of the class.
 *
 * @author Harrison Mega
 * @version 1
 *
 */
public interface IUserDAO<T> {
    void createTable();
    void insertNew(T entity);
    void delete(T entity);
    void update(T entity);
    List<T> getAll();
    T getAllBool(boolean bool);
}

