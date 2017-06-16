package com.pucmm.practica4.entidades;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

/**
 * Created by anyderre on 04/06/17.
 */
@Entity
public class Articulo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String titulo;
    @Column(unique = true)
    @Lob
    private String cuerpo;
    @OneToOne()
    private Usuario autor;
    private Date fecha;

    @OneToMany(mappedBy = "articulo")
    private List<Comentario> comentarios;
    @OneToMany(mappedBy = "articulo")
    private List<Etiqueta> etiquetas;

    @OneToMany
    private List<ArticleLike>articleLikes;
    @OneToMany
    private List<ArticleDislike>articleDislikes;

    public Articulo(String titulo, String cuerpo, Usuario autor, Date fecha, List<Comentario> comentarios, List<Etiqueta> etiquetas, List<ArticleLike> articleLikes, List<ArticleDislike> articleDislikes) {
        this.titulo = titulo;
        this.cuerpo = cuerpo;
        this.autor = autor;
        this.fecha = fecha;
        this.comentarios = comentarios;
        this.etiquetas = etiquetas;
        this.articleLikes = articleLikes;
        this.articleDislikes = articleDislikes;
    }

    public Articulo() {
    }

    public List<ArticleLike> getArticleLikes() {
        return articleLikes;
    }

    public void setArticleLikes(List<ArticleLike> articleLikes) {
        this.articleLikes = articleLikes;
    }

    public List<ArticleDislike> getArticleDislikes() {
        return articleDislikes;
    }

    public void setArticleDislikes(List<ArticleDislike> articleDislikes) {
        this.articleDislikes = articleDislikes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }


    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public List<Etiqueta> getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(List<Etiqueta> etiquetas) {
        this.etiquetas = etiquetas;
    }

}