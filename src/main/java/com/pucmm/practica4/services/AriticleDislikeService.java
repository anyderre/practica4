package com.pucmm.practica4.services;

import com.pucmm.practica4.entidades.ArticleDislike;
import com.pucmm.practica4.entidades.Articulo;


import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by anyderre on 15/06/17.
 */
public class AriticleDislikeService extends GestionDb<ArticleDislike> {
    private static AriticleDislikeService instancia;

    private AriticleDislikeService(){
        super(ArticleDislike.class);
    }

    public static AriticleDislikeService getInstancia(){
        if(instancia==null){
            instancia = new AriticleDislikeService();
        }
        return instancia;
    }

    public List<ArticleDislike> findAllByArticulo(Articulo articulo){
        EntityManager entityManager = getEntityManager();
        Query query= entityManager.createQuery("select a from ArticleDislike a where a.articulo like :articulo");
        query.setParameter("articulo", articulo);
        return query.getResultList();
    }
    public Boolean deleteAllByArticulo(Articulo articulo){
        EntityManager entityManager = getEntityManager();
        Query query= entityManager.createQuery("delete from ArticleDislike a where a.articulo like :articulo");
        query.setParameter("articulo", articulo);
        return true;
    }
}
