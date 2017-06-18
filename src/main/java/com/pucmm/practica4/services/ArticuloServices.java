package com.pucmm.practica4.services;


import com.pucmm.practica4.services.*;
import com.pucmm.practica4.entidades.Articulo;;

import com.pucmm.practica4.services.GestionDb;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by john on 05/06/17.
 */
public class ArticuloServices extends GestionDb<Articulo> {

    private static ArticuloServices instancia;

    private ArticuloServices() {
        super(Articulo.class);
    }

    public static ArticuloServices getInstancia(){
        if (instancia==null){
            instancia= new ArticuloServices();
        }
        return instancia;
    }

    public List<Articulo> findAllArticuloBetweenTwoIds(int val1){
        EntityManager entityManager = getEntityManager();
        Query query= entityManager.createQuery( "select a from Articulo a");
        if(val1<0){
            val1 =5+val1;
            query.setFirstResult(0);
            query.setMaxResults(val1);
        }else{
            query.setFirstResult(val1);
            query.setMaxResults(5);
        }

        return query.getResultList();
    }

}