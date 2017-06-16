package com.pucmm.practica4.entidades;

/**
 * Created by anyderre on 15/06/17.
 */
import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by anyderre on 15/06/17.
 */
@Entity
@NamedQueries({@NamedQuery(name = "Dislike.findAllByComentario", query = "select l from Dislike l where l.comentario like :comentario")})
public class Dislike implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    private Usuario autor;

    @ManyToOne
    private Comentario comentario;

    public Dislike( Usuario autor,Comentario comentario) {
        this.autor = autor;
        this.comentario=comentario;
    }
    public Dislike(){

    }

    public Comentario getComentario() {
        return comentario;
    }

    public void setComentario(Comentario comentario) {
        this.comentario = comentario;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }
}
