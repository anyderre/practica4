package com.pucmm.practica4.main;

import com.pucmm.practica4.entidades.Articulo;
import com.pucmm.practica4.entidades.Comentario;
import com.pucmm.practica4.entidades.Etiqueta;
import com.pucmm.practica4.entidades.Usuario;
import com.pucmm.practica4.services.*;
import com.sun.org.apache.regexp.internal.RE;
import freemarker.template.Configuration;

import spark.ModelAndView;
import spark.Session;
import spark.template.freemarker.FreeMarkerEngine;

import java.sql.*;
import java.util.*;
import java.util.Date;

import static java.lang.Class.forName;
import static spark.Spark.*;
import static spark.debug.DebugScreen.enableDebugScreen;
import static spark.route.HttpMethod.*;

/**
 * Created by john on 03/06/17.
 */
public class Main {

    public static void main(String[] args)throws SQLException {

        //Seteando el puerto en Heroku
        port(getHerokuAssignedPort());
        enableDebugScreen();

        //indicando los recursos publicos.
        staticFiles.location("/publico");

        //Starting database
        BootStrapServices.getInstancia().init();


            //Adding admin user
            UsuarioServices usuarioServices = UsuarioServices.getInstancia();

                Usuario insertar = new Usuario();
                insertar.setAdministrador(true);
                insertar.setId(1);
                insertar.setAutor(true);
                insertar.setNombre("Jhon Ridore");
                insertar.setPassword("1234");
                insertar.setUsername("anyderre");
        System.out.println("there");
             if(usuarioServices.getUsuario("anyderre").isEmpty()){
                 System.out.println("there");
                usuarioServices.crear(insertar);

            }


        //Indicando la carpeta por defecto que estaremos usando.
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);
        configuration.setClassForTemplateLoading(Main.class, "/templates");
        FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine(configuration);


        get("/", (request, response) -> {
                    Map<String, Object> attributes = new HashMap<>();

                    ArticuloServices articuloServices = ArticuloServices.getInstancia();
                    ArrayList<Articulo> articulos = (ArrayList<Articulo>) articuloServices.findAll();
                    EtiquetaServices etiquetaServices = EtiquetaServices.getInstancia();
                    ComentarioServices comentarioServices = ComentarioServices.getInstancia();
                    List<Etiqueta> etiquetas = null;
                    List<Comentario>comentarios=null;
                    List<Articulo> articulosTemp=new ArrayList<>();
                    for(Articulo articulo: articulos){
                        etiquetas= etiquetaServices.findAllByArticulo(articulo);
                        comentarios= comentarioServices.findAllByArticulo(articulo);
                        articulo.setEtiquetas(etiquetas);
                        articulo.setComentarios(comentarios);
                        articulosTemp.add(articulo);
                    }
                    //inversing ArrayList order
                    Collections.reverse(articulosTemp);
                    attributes.put("articulos", articulosTemp);

            if (articulos.size()==0) {
                attributes.put("noDatos", "Todavia no hay Articulos en la base de datos");

            };
            System.out.println(articulos.size());

            attributes.put("titulo", "Welcome");
           // attributes.put("articulos", articulos);
            return new ModelAndView(attributes, "index.ftl");
        }, freeMarkerEngine);



        get("/login", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("titulo", "Login");
            return new ModelAndView(attributes, "login.ftl");
        }, freeMarkerEngine);

        post("/login", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            //Usuario currentUserLogin = new Usuario();
            Session session = request.session(true);
            Usuario usuario = new Usuario();
            String username = request.queryParams("username");
            String password = request.queryParams("password");
            usuario.setUsername(username);
            usuario.setPassword(password);
            UsuarioServices usuarioServices1 = UsuarioServices.getInstancia();

            List<Usuario> usuarios = usuarioServices1.getUsuario(username);
           if(usuarios.get(0).getNombre()!=null){

                 if(usuarios.get(0).getUsername().equals(username) && usuarios.get(0).getPassword().equals(password)) {
                     usuario.setId(usuarios.get(0).getId());
                     usuario.setAutor(usuarios.get(0).getAutor());
                     usuario.setAdministrador(usuarios.get(0).getAdministrador());
                     usuario.setNombre(usuarios.get(0).getNombre());
                     session.attribute("usuario", usuario);
                     response.redirect("/");
                 }
           }
           attributes.put("message", "Lo siento no tienes cuenta registrada solo un admin puede registrarte");
            attributes.put("titulo", "login");

            return new ModelAndView(attributes, "login.ftl");
        }, freeMarkerEngine);


 //------------------------------------------//Admin task------------------------------------------------------------
        before("/registrar", (request, response) -> {
            Usuario usuario = request.session(true).attribute("usuario");

            if (usuario == null) {
                response.redirect("/login");
            }

        });
        get("/registrar", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();

            attributes.put("titulo", "Registrar");
            return new ModelAndView(attributes, "registrar.ftl");
        }, freeMarkerEngine);


        post("/registrar/usuario", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();

            if(request.queryParams("password").matches(request.queryParams("password-confirm"))){
                Usuario usuario = request.session(true).attribute("usuario");

                if(usuario.getAdministrador()){
                    Usuario newUsuario = new Usuario();
                    newUsuario.setNombre(request.queryParams("nombre"));
                    newUsuario.setAdministrador(false);
                    newUsuario.setAutor(true);
                    newUsuario.setPassword(request.queryParams("password"));
                    newUsuario.setUsername(request.queryParams("username"));
                    UsuarioServices usuarioServices1 = UsuarioServices.getInstancia();
                    usuarioServices1.crear(newUsuario);
                    response.redirect("/");
                }else{
                    attributes.put("message", "Solo administrador puede crear usario");
                }
            }else{
                attributes.put("confirm", "password doesn't match");
            }
            attributes.put("titulo", "Registrar");
            return new ModelAndView(attributes,"registrar.ftl");
        },freeMarkerEngine);


//<--------------------------------------------------Etiquetas crud------------------------------------------------------------------------------------------------------------------->
      delete("/etiqueta/:articulo/borrar/:id",(request, response)->{
          long id=0,articulo=0;
          try{
               id = Long.parseLong(request.params("id"));
               articulo = Long.parseLong(request.params("articulo"));
          }catch (Exception ex){
              ex.printStackTrace();
          }
          EtiquetaServices etiquetaServices = EtiquetaServices.getInstancia();
          etiquetaServices.delete(id);
          response.redirect("/ver/articulo/"+articulo);
          return "";
    });


//<--------------------------------------------------Comentario crud------------------------------------------------------------------------------------------------------------------->

        post("/agregar/comentario/:articulo", (request, response)->{
           // long articulo=0;

            long articulo= Long.parseLong((request.params("articulo")));

            Session session = request.session(true);
            Usuario usuario = session.attribute("usuario");
            System.out.println(usuario.getUsername());

            ArticuloServices articuloServices = ArticuloServices.getInstancia();
            Comentario comentario1=new Comentario();
            comentario1.setAutor(usuario);
            comentario1.setComentario(request.queryParams("comentario"));
            comentario1.setArticulo(articuloServices.find(articulo));


            ComentarioServices comentarioServices = ComentarioServices.getInstancia();
            comentarioServices.crear(comentario1);

            response.redirect("/ver/articulo/"+articulo);

          return "";
        });

        before("/agregar/comentario/:articulo", (request, response) -> {
            Usuario usuario = request.session(true).attribute("usuario");
            if (usuario == null) {
                response.redirect("/login");
            }
        });

//<--------------------------------------------------Articulo Crud------------------------------------------------------------------------------------------------------------------->
        get("/agregar/articulo", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            Usuario usuario = request.session(true).attribute("usuario");

            model.put("titulo", "registrar articulo");
            return new ModelAndView(model, "registrarArticulo.ftl");
        },freeMarkerEngine);

        //checking if user have a session
        before("/agregar/articulo", (request, response) -> {
            Usuario usuario = request.session(true).attribute("usuario");

            if (usuario == null) {
                response.redirect("/login");
            }
        });

        post("/agregar/articulo",(request, response)->{
            String []etiquetas=request.queryParams("etiquetas").split(",");
            //String autor = request.queryParams("username");
            ArticuloServices articuloServices=ArticuloServices.getInstancia();
            Session session = request.session(true);

            Usuario usuario = session.attribute("usuario");


            //System.out.println(usuario);
            Articulo articulo = new Articulo();
            articulo.setTitulo( request.queryParams("titulo"));
            articulo.setCuerpo(request.queryParams("cuerpo"));

            articulo.setAutor(usuario);
            articulo.setFecha(new Date());
            articulo.setLikes(0);
            articulo.setDislikes(0);

            articuloServices.crear(articulo);

            //getting the recent ID
            ArticuloServices articuloServices1=ArticuloServices.getInstancia();
            List <Articulo>articulos= articuloServices1.findAll();
           // System.out.println(articulos.get(0).getAutor()+"=======================================");
            long id = articulos.get(articulos.size()-1).getId();

            if(etiquetas.length!=0){
                EtiquetaServices etiquetaServices = EtiquetaServices.getInstancia();
                articulo.setId(id);
                for(String et: etiquetas){
                    etiquetaServices.crear(new Etiqueta(et,articulo));
                }
            }else{
                System.out.println("Error al entrar las etiquetas");
            }

            response.redirect("/");
            return "";
        });


        get("/ver/articulo/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            Usuario usuario = request.session(true).attribute("usuario");

            long id= (long)Integer.parseInt(request.params("id"));
            ArticuloServices articuloServices = ArticuloServices.getInstancia();
            Articulo articulo = articuloServices.find(id);
            EtiquetaServices etiquetaServices =EtiquetaServices.getInstancia();
            ComentarioServices comentarioServices =ComentarioServices.getInstancia();
            List<Etiqueta> etiquetas = null;
            List<Comentario>comentarios=null;

            etiquetas= etiquetaServices.findAllByArticulo(articulo);
            comentarios= comentarioServices.findAllByArticulo(articulo);
            articulo.setEtiquetas(etiquetas);
            articulo.setComentarios(comentarios);


            model.put("titulo", "Welcome");
            model.put("articulo", articulo);

            model.put("titulo", "Ver articulo");
            return new ModelAndView(model, "verArticulo.ftl");
        },freeMarkerEngine);

//------------------------modifying articulo---------------------------
        get("/modificar/articulo/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            Usuario usuario = request.session(true).attribute("usuario");
            ArticuloServices articuloServices =ArticuloServices.getInstancia();
            Articulo articulo = articuloServices.find(Long.parseLong(request.params("id")));
            EtiquetaServices etiquetaServices = EtiquetaServices.getInstancia();

            List<Etiqueta> etiquetas =etiquetaServices.findAllByArticulo(articulo);

            model.put("articulo",articulo);
            model.put("etiquetas",etiquetas);
            model.put("titulo", "registrar articulo");
            return new ModelAndView(model, "modificarArticulo.ftl");
        },freeMarkerEngine);

        //checking if user have a session
        before("/modificar/articulo/:id", (request, response) -> {
            Usuario usuario = request.session(true).attribute("usuario");
            if (usuario == null) {
                response.redirect("/login");
            }
        });

        post("/modificar/:id/articulo",(request, response)->{
            Long id = Long.parseLong(request.params("id"));
            String []etiquetas=request.queryParams("etiquetas").split(",");
            //String autor = request.queryParams("username");
            ArticuloServices articuloServices=ArticuloServices.getInstancia();
            Session session = request.session(true);

            Usuario usuario = session.attribute("usuario");
            //System.out.println(usuario);
            Articulo articulo = new Articulo();
            articulo.setTitulo( request.queryParams("titulo"));
            articulo.setCuerpo(request.queryParams("cuerpo"));

            articulo.setAutor(usuario);
            articulo.setFecha(new Date());
            articulo.setId(id);
            System.out.println();
            if(etiquetas.length!=0){
                EtiquetaServices etiquetaServices = EtiquetaServices.getInstancia();

                List<Etiqueta>etiquetas1 = etiquetaServices.findAllByArticulo(articulo);
                int count =0;
                for(String et: etiquetas){
                    if(!etiquetas1.get(0).getEtiqueta().equals(et))
                        etiquetaServices.crear(new Etiqueta(et,articulo));
                    count+=1;
                }
            }else{
                System.out.println("Error al entrar las etiquetas");
            }
            System.out.println("there");
            articuloServices.editar(articulo);

            response.redirect("/ver/articulo/"+id);
            return "";
        });

        before("/borrar/articulo/:articulo", (request, response) -> {
            Usuario usuario = request.session(true).attribute("usuario");
            if (usuario == null) {
                response.redirect("/login");
            }
        });

        get("/borrar/articulo/:articulo",(request, response)->{
            long articulo=0;
            try{
                articulo = Long.parseLong(request.params("articulo"));
            }catch (Exception ex){
                ex.printStackTrace();
            }
            EtiquetaServices etiquetaServices =EtiquetaServices.getInstancia();
            ComentarioServices comentarioServices = ComentarioServices.getInstancia();
            ArticuloServices articuloServices = ArticuloServices.getInstancia();

            etiquetaServices.delete(articulo);
            comentarioServices.delete(articulo);
            articuloServices.delete(articulo);
            response.redirect("/");
            return "";
        });

    }  /**
     * Metodo para setear el puerto en Heroku
     * @return
     */
    private static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
}

