package ua.forum.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO<T, V extends Serializable> {
    T find(V id);
    V create(T entity);
    void update(T entity);
    void delete(V id);
    List<T> findAll();
}
