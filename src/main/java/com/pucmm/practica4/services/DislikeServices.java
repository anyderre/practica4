package com.pucmm.practica4.services;

import com.pucmm.practica4.entidades.Articulo;
import com.pucmm.practica4.entidades.Comentario;
import com.pucmm.practica4.entidades.Dislike;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by anyderre on 15/06/17.
 */
public class DislikeServices extends GestionDb<Dislike> {
    private static DislikeServices instancia;

    private DislikeServices(){
        super(Dislike.class);
    }

    public static DislikeServices getInstancia(){
        if(instancia==null){
            instancia = new DislikeServices();
        }
        return instancia;
    }

    public List<Dislike> findAllByComentario(Comentario comentario){
        EntityManager entityManager = getEntityManager();
        Query query= entityManager.createQuery("select d from Dislike d where d.comentario like :comentario");
        query.setParameter("comentario", comentario);
        return query.getResultList();
    }
    public Boolean deleteAllByComentario(Comentario comentario){
        EntityManager entityManager = getEntityManager();
        Query query= entityManager.createQuery("delete from Dislike a where a.comentario like :comentario");
        query.setParameter("comentario", comentario);
        return true;
    }
}
