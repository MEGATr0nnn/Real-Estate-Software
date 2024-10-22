package com.example.real_estate_software.model;

import java.util.List;

/**
 * Interface for any DAOs of Object type User, this interface takes a double generic type specification, ie main type Tenant, additional type Property.
 * This is used in User classes where there may be a dependence on values in other class instances.
 *
 * @param <T> The type of entity that this class will handle (Owner, Tenant, Property etc.). This entity must remain consistent for the entire application of the class.
 *
 * @author Harrison Mega
 * @version 1
 *
 * */
public interface IUserDAODoubleGeneric<T,U> {
    void createTable();
    void insertNew(T entity, U additionalentity);
    void delete(T entity);
    void update(T entity, U additonalentity);
    List<T> getAll();
    T getAllBool(boolean bool, U additionalentity);
}
