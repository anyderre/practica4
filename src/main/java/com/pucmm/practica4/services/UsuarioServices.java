package services;

import com.modelo.*;

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
public class UsuarioServices {

    public List<Usuario> listarUsuarios(){
        List<Usuario> usuarios = new ArrayList<>();
        Connection connection = null;
        String query = "select * from usuario;";

        try {

            connection= DataBaseServices.getInstancia().getConexion();
            //
            PreparedStatement preparedStatement =connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Usuario usuario = new Usuario();
                usuario.setId(resultSet.getLong("id"));
                usuario.setAdministrador(resultSet.getBoolean("ADMINISTRADOR"));
                usuario.setAutor(resultSet.getBoolean("AUTOR"));
                usuario.setNombre(resultSet.getString("nombre"));
                usuario.setPassword(resultSet.getString("password"));
                usuario.setUsername(resultSet.getString("username"));
                usuarios.add(usuario);
            }
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarios;
    }
    public Usuario getUsuario(String username)throws NullPointerException{
        Usuario usuario = new Usuario();
        String query = "select * from USUARIO where username=?;";

        Connection connection = DataBaseServices.getInstancia().getConexion();
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,username);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                usuario.setId(resultSet.getLong("id"));
                usuario.setUsername(resultSet.getString("username"));
                usuario.setPassword(resultSet.getString("password"));
                usuario.setNombre(resultSet.getString("nombre"));
                usuario.setAutor(resultSet.getBoolean("autor"));
                usuario.setAdministrador(resultSet.getBoolean("administrador"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return usuario;
    }

    public boolean crearUsuario(Usuario usuario){
        boolean ok = false;
        Connection connection= DataBaseServices.getInstancia().getConexion();
        String query = "insert into usuario (username,nombre, password, administrador, autor)values(?,?,?,?,?);";

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,usuario.getUsername());
            preparedStatement.setString(2,usuario.getNombre());
            preparedStatement.setString(3,usuario.getPassword());
            preparedStatement.setBoolean(4,usuario.getAdministrador());
            preparedStatement.setBoolean(5,usuario.getAutor());
            if (preparedStatement.executeUpdate()>0){
                ok=true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                assert connection != null;
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return  ok;
    }

    public boolean actualizarUsuario(Usuario usuario){
        boolean ok = false;
        Connection connection= null;
        String query = "update usuario set username=?,nombre=?, password=?, administrador=?, autor=? WHERE username=?);";
        try {
            connection= DataBaseServices.getInstancia().getConexion();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,usuario.getUsername());
            preparedStatement.setString(2,usuario.getNombre());
            preparedStatement.setString(3,usuario.getPassword());
            preparedStatement.setBoolean(4,usuario.getAdministrador());
            preparedStatement.setBoolean(5,usuario.getAutor());
            //resolving where
            preparedStatement.setString(6,usuario.getUsername());

            if (preparedStatement.executeUpdate()>0){
                ok=true;
            };
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                assert connection != null;
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return  ok;
    }
    public boolean borrarUsuario(String usuario){
        boolean ok = false;

        Connection connection = null;
        String query = "delete from usuario where username=?;";
        connection = DataBaseServices.getInstancia().getConexion();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            //resolving where
            preparedStatement.setString(1,usuario);

            if (preparedStatement.executeUpdate()>0){
                ok=true;
            };
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return  ok;

    }

}
