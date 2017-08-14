package ua.forum.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ua.forum.dao.GenericDAO;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class GenericDAOImpl<T, V extends Serializable> implements GenericDAO<T,V> {

    @Autowired
    private SessionFactory sessionFactory;
    protected Class type;

    public GenericDAOImpl() {
        ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
        type = (Class) parameterizedType.getActualTypeArguments()[0];
    }

    @Override
    public T find(V id) {
        return (T) getSession().get(type, id);
    }

    @Override
    public V create(T entity) {
        return (V) getSession().save(entity);
    }

    @Override
    public void update(T entity) {
        getSession().update(entity);
    }

    @Override
    public void delete(V id) {
        T t = (T) getSession().load(type, id);
        getSession().delete(t);

    }

    @Override
    public List<T> findAll() {
        return getSession().createCriteria(type).list();
    }

    protected Session getSession(){
        return sessionFactory.getCurrentSession();
    }
}
