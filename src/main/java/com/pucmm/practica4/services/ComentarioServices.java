package com.pucmm.practica4.services;

import com.pucmm.practica4.entidades.Comentario;
import com.pucmm.practica4.services.GestionDb;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by john on 05/06/17.
 */
public class ComentarioServices extends GestionDb<Comentario> {
    private static ComentarioServices instancia;

    private ComentarioServices(){
        super(Comentario.class);
    }

    public static ComentarioServices getInstancia(){
        if(instancia==null){
            instancia = new ComentarioServices();
        }
        return instancia;
    }


}
