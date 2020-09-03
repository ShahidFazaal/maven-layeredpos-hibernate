package dao;

import entity.SuperEntity;
import org.hibernate.Session;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class CrudDAOImpl<T extends SuperEntity,ID extends Serializable> implements CrudDAO<T,ID> {
    Class<T> entity;
    protected Session session;
    public CrudDAOImpl() {
        entity = (Class<T>)(((ParameterizedType) (this.getClass().getGenericSuperclass())).getActualTypeArguments()[0]);
    }

    @Override
    public List<T> findAll() throws Exception {
        return session.createQuery("FROM "+entity.getName()).list();
    }

    @Override
    public T find(ID pk) throws Exception {
        return session.get(entity,pk);
    }

    @Override
    public void save(T entity) throws Exception {
        session.save(entity);
    }

    @Override
    public void update(T entity) throws Exception {
        session.update(entity);
    }

    @Override
    public void delete(ID key) throws Exception {
        session.delete(session.get(entity,key));
    }

    @Override
    public void setSession(Session session) {
        this.session=session;
    }
}
