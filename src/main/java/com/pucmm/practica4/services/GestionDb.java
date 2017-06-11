package com.pucmm.practica4.services;

/**
 * Created by anyderre on 11/06/17.
 */
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Id;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.lang.reflect.Field;
import java.util.List;
public class GestionDb<T> {
    private EntityManagerFactory entityManagerFactory;
    private Class<T> entidad;

    public GestionDb(Class<T> entidad){
        this.entidad =entidad;
        if(entityManagerFactory==null){
            entityManagerFactory= Persistence.createEntityManagerFactory("MiUnidadPersistencia");
        }

    }
    public EntityManager getEntityManager(){
        return entityManagerFactory.createEntityManager();
    }

    private Object getFieldValue(T entidad){
        for(Field field: entidad.getClass().getDeclaredFields()){
            if(field.isAnnotationPresent(Id.class)){
                try{
                    field.setAccessible(true);
                    Object f = field.get(entidad);
                    return f;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private void crear(T entidad){
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        try {

            entityManager.merge(entidad);
            entityManager.getTransaction().commit();
        }catch (Exception ex){
            entityManager.getTransaction().rollback();
        }finally {
            entityManager.close();
        }
    }

    private void editar(T entidad){
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        try{
            entityManager.merge(entidad);
            entityManager.getTransaction().commit();
        }catch (Exception e){
            entityManager.getTransaction().rollback();
            throw e;
        }finally {
            entityManager.close();
        }
    }

    private void delete (Object entidadId){
        EntityManager entityManager =getEntityManager();
        entityManager.getTransaction().begin();
        try{
            T entidad = entityManager.find(this.entidad,entidadId);
            entityManager.remove(entidad);
            entityManager.getTransaction().commit();
        }catch (Exception ex){
            entityManager.getTransaction().rollback();
            throw ex;
        }finally {
            entityManager.close();
        }
    }

    private T find(Object id){
        EntityManager entityManager = getEntityManager();
        try {
            return entityManager.find(this.entidad,id);
        }catch (Exception e){
            throw e;
        }finally {
            entityManager.close();
        }
    }
    private List<T>findAll(){
        EntityManager entityManager = getEntityManager();
        try {
            CriteriaQuery<T> criteriaQuery = entityManager.getCriteriaBuilder().createQuery(this.entidad);
            criteriaQuery.select(criteriaQuery.from(this.entidad));
            return entityManager.createQuery(criteriaQuery).getResultList();
        }catch (Exception e){
            throw e;
        }finally {
            entityManager.close();
        }
    }
}