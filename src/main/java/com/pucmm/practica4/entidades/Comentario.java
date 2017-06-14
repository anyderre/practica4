package com.pucmm.practica4.entidades;

import javax.persistence.*;

/**
 * Created by john on 04/06/17.
 */
@Entity
@NamedQueries({@NamedQuery(name = "Comentario.findAllByArticulo", query = "select c from Comentario c where c.articulo like :articulo")})
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int likes;
    private int dislikes;
    @Lob
    private String comentario;
    @OneToOne
    private Usuario autor;
    @ManyToOne()
    private Articulo articulo;


    public Comentario(int likes, int dislikes, String comentario, Usuario autor, Articulo articulo) {
        this.likes = likes;
        this.dislikes = dislikes;
        this.comentario = comentario;
        this.autor = autor;
        this.articulo = articulo;
    }

    public Comentario() {
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
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