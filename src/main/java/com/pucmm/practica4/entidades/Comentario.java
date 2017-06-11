package com.pucmm.practica4.entidades;

import javax.persistence.*;

/**
 * Created by john on 04/06/17.
 */
@Entity
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String comentario;
    @OneToOne
    private Usuario autor;
    @ManyToOne()
    private Articulo articulo;

    public Comentario( long id, String comentario, Usuario autor, Articulo articulo) {
        this.id =id;
        this.comentario = comentario;
        this.autor = autor;
        this.articulo = articulo;
    }

    public Comentario() {
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
