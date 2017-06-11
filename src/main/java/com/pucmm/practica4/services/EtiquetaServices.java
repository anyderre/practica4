package services;

import com.modelo.Articulo;
import com.modelo.Etiqueta;
import com.modelo.Usuario;
import com.pucmm.practica4.entidades.Etiqueta;
import com.pucmm.practica4.services.ComentarioServices;
import com.pucmm.practica4.services.GestionDb;

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

}
