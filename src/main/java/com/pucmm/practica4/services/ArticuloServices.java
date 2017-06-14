package com.pucmm.practica4.services;

import com.pucmm.practica4.services.*;
import com.pucmm.practica4.entidades.Articulo;;

import com.pucmm.practica4.services.GestionDb;

import javax.persistence.EntityManager;
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


}