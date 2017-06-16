package com.pucmm.practica4.entidades;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by anyderre on 15/06/17.
 */
@Entity
@NamedQueries({@NamedQuery(name = "ArticleDislike.findAllByArticulo", query = "select l from ArticleDislike l where l.articulo like :articulo")})
public class ArticleDislike implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    private Usuario autor;

    @ManyToOne
    private Articulo articulo;

    public ArticleDislike( Usuario autor,Articulo articulo) {
        this.autor = autor;
        this.articulo =articulo;
    }
    public ArticleDislike(){

    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }
}

