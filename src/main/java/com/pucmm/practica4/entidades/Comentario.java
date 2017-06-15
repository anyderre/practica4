package com.pucmm.practica4.entidades;

import javax.persistence.*;
import java.util.List;

/**
 * Created by john on 04/06/17.
 */
@Entity
@NamedQueries({@NamedQuery(name = "Comentario.findAllByArticulo", query = "select c from Comentario c where c.articulo like :articulo")})
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Lob
    private String comentario;
    @OneToOne
    private Usuario autor;
    @ManyToOne()
    private Articulo articulo;
    @OneToMany
    private List<Likes>likes;
    @OneToMany
    private List<Dislike>dislikes;

    public Comentario(String comentario, Usuario autor, Articulo articulo, List<Likes> likes, List<Dislike> dislikes) {
        this.comentario = comentario;
        this.autor = autor;
        this.articulo = articulo;
        this.likes = likes;
        this.dislikes = dislikes;
    }

    public Comentario() {
    }

    public List<Likes> getLikes() {
        return likes;
    }

    public void setLikes(List<Likes> likes) {
        this.likes = likes;
    }

    public List<Dislike> getDislikes() {
        return dislikes;
    }

    public void setDislikes(List<Dislike> dislikes) {
        this.dislikes = dislikes;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }


}