package services;

import com.modelo.Articulo;
import com.modelo.Etiqueta;
import com.modelo.Usuario;

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
public class EtiquetaServices {




    public List<Etiqueta> getAllEtiquetas(long articulo){
        List<Etiqueta> etiquetas = new ArrayList<>();
        Connection connection = null;
        String query = "select * from etiqueta WHERE articulo=?;";

        try {

            connection= DataBaseServices.getInstancia().getConexion();
            ArticuloServices articuloServices = new ArticuloServices();

            PreparedStatement preparedStatement =connection.prepareStatement(query);
            preparedStatement.setLong(1,articulo);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Etiqueta etiqueta = new Etiqueta();
                etiqueta.setEtiqueta(resultSet.getString("etiqueta"));
                etiqueta.setId(resultSet.getLong("id"));
                etiqueta.setArticulo(articuloServices.getArticulo(resultSet.getLong("articulo")));
                //adding etiqueta to list
                etiquetas.add(etiqueta);
            }
            connection.close();

        } catch (SQLException ex) {
            Logger.getLogger(EtiquetaServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(EtiquetaServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return etiquetas;
    }
    public Etiqueta getEtiqueta(long id){
        Etiqueta etiqueta = new Etiqueta();
        String query = "select * from etiqueta where id=?;";
        ArticuloServices articuloServices = new ArticuloServices();
        Connection connection = DataBaseServices.getInstancia().getConexion();
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                etiqueta.setId(1);
                etiqueta.setId(resultSet.getLong("id"));
                etiqueta.setArticulo(articuloServices.getArticulo(resultSet.getLong("articulo")));
                etiqueta.setId(resultSet.getLong("id"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(EtiquetaServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(EtiquetaServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return etiqueta;
    }

    public boolean crearEtiqueta(Etiqueta etiqueta){
        boolean ok = false;
        Connection connection= null;
        String query = "insert into etiqueta (etiqueta,articulo)values(?,?);";

        try {
            connection = DataBaseServices.getInstancia().getConexion();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,etiqueta.getEtiqueta());
            preparedStatement.setLong(2,etiqueta.getArticulo().getId());
           ;
            if (preparedStatement.executeUpdate()>0){
                ok=true;
            };
        } catch (SQLException ex) {
            Logger.getLogger(EtiquetaServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(EtiquetaServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return  ok;
    }
    public boolean actualizarEtiqueta(Etiqueta etiqueta){
        boolean ok = false;
        Connection connection= null;
        String query = "update etiqueta set id=?,etiqueta=?, articulo=? WHERE id=?);";
        try {
            connection = DataBaseServices.getInstancia().getConexion();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1,etiqueta.getId());
            preparedStatement.setString(2,etiqueta.getEtiqueta());
            preparedStatement.setLong(3,etiqueta.getArticulo().getId());

            //resolving where
            preparedStatement.setLong(1,etiqueta.getId());

            if (preparedStatement.executeUpdate()>0){
                ok=true;
            };
        } catch (SQLException ex) {
            Logger.getLogger(EtiquetaServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(EtiquetaServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return  ok;
    }
    public boolean borrarEtiqueta(long articulo){
        boolean ok = false;

        Connection connection = null;
        String query = "delete from etiqueta where articulo=?;";
        connection = DataBaseServices.getInstancia().getConexion();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            //resolving where
            preparedStatement.setLong(1,articulo);

            if (preparedStatement.executeUpdate()>0){
                ok=true;
            };
        } catch (SQLException ex) {
            Logger.getLogger(EtiquetaServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(EtiquetaServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return  ok;

    }

}
