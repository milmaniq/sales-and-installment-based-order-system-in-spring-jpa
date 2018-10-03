package lk.ijse.rj.dao;

import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class CrudDAOImpl<T,ID> implements CrudDAO<T, ID> {

    @PersistenceContext
    protected EntityManager entityManager;

    private Class<T> entity;

    public CrudDAOImpl() {
        entity = (Class<T>)((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }



    @Override
    public void insert(T entity) throws Exception {
        entityManager.persist(entity);
    }

    @Override
    public void update(T entity) throws Exception {
        entityManager.merge(entity);
    }

    @Override
    public void delete(ID id) throws Exception {



        if(entity.getName() == "lk.ijse.rj.entity.OrderBalancePayment"){
            T t = entityManager.find(entity, Integer.valueOf(id.toString()));
            entityManager.remove(t);
            return;
        }

        T t = entityManager.find(entity, id);
        entityManager.remove(t);
    }

    @Override
    public T find(ID id) throws Exception {
        return entityManager.find(entity, id);
    }

    @Override
    public List<T> getAll() throws Exception {
        return entityManager.createQuery("FROM " + entity.getName()).getResultList();
    }



}
