package com.pucmm.practica4.services;

import com.pucmm.practica4.entidades.Articulo;
import com.pucmm.practica4.entidades.Comentario;
import com.pucmm.practica4.entidades.Likes;


import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by anyderre on 15/06/17.
 */
public class LikesServices extends GestionDb<Likes> {

        private static LikesServices instancia;

        private LikesServices(){
            super(Likes.class);
        }

        public static LikesServices getInstancia(){
            if(instancia==null){
                instancia = new LikesServices();
            }
            return instancia;
        }

        public List<Likes> findAllByComentario(Comentario comentario){
            EntityManager entityManager = getEntityManager();
            Query query= entityManager.createQuery("select l from Likes l where l.comentario like :comentario");
            query.setParameter("comentario", comentario);
            return query.getResultList();
            }
        public Boolean deleteAllByComentario(Comentario comentario){
            EntityManager entityManager = getEntityManager();
            Query query= entityManager.createQuery("delete from Likes a where a.comentario like :comentario");
            query.setParameter("comentario", comentario);
            return true;
        }
    }


