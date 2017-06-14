package com.pucmm.practica4.services;

import com.pucmm.practica4.entidades.Articulo;
import com.pucmm.practica4.entidades.Etiqueta;
import com.pucmm.practica4.services.ComentarioServices;
import com.pucmm.practica4.services.GestionDb;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by john on 05/06/17.
 */
public class EtiquetaServices extends GestionDb<Etiqueta> {
    private static EtiquetaServices instancia;

    private EtiquetaServices(){super(Etiqueta.class);}

    public static EtiquetaServices getInstancia(){
        if(instancia==null){
            instancia = new EtiquetaServices();
        }
        return instancia;
    }

    public List<Etiqueta> findAllByArticulo(Articulo articulo){
        EntityManager em=getEntityManager();
        Query query=em.createNamedQuery("Etiqueta.findAllByArticulo");
        query.setParameter("articulo",articulo);
        return query.getResultList();
    }

    public List<Etiqueta> findAllByEtiqueta(String etiqueta){
        EntityManager em = getEntityManager();
        Query query = em.createQuery("select e from Etiqueta e where e.etiqueta like :etiqueta");
        query.setParameter("etiqueta",etiqueta+"%");
        return query.getResultList();
    }

}
