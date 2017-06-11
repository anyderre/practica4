package com.pucmm.practica4.entidades;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.lang.reflect.Field;

/**
 * Created by john on 04/06/17.
 */
@Entity
@NamedQueries({@NamedQuery(name="Etiqueta.findAllByArticulo", query = "select e from Etiqueta e where e.articulo like :articulo")})
public class Etiqueta implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String etiqueta;
    @ManyToOne()
    private Articulo articulo;

    public Etiqueta(String etiqueta, Articulo articulo) {
        this.etiqueta = etiqueta;
        this.articulo = articulo;
    }

    public Etiqueta() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }
}
