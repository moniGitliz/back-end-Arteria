package com.Arteria.ArteriaBackend.model;


import jakarta.persistence.*;

@Entity
public class Obra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Aqui se establece como un AUTOINCREMENT
    private Integer id_obra; //PrimaryKey

    @Column(nullable = false, length = 50)
    private String nombre_obra;

    @Column(nullable = false, length = 150)
    private String descripcion_obra;

    @Column(nullable = false)
    private Double precio_obra;

    @Column(nullable = false)
    private Integer estado_obra;

    @Column(nullable = false, length = 50)
    private String nombre_artista;


    public Obra() {
    }

    public Obra(Integer id_obra, String nombre_obra, String descripcion_obra, Double precio_obra, Integer estado_obra, String nombre_artista) {
        this.id_obra = id_obra;
        this.nombre_obra = nombre_obra;
        this.descripcion_obra = descripcion_obra;
        this.precio_obra = precio_obra;
        this.estado_obra = estado_obra;
        this.nombre_artista = nombre_artista;
    }

    public Integer getId_obra() {
        return id_obra;
    }

    public void setId_obra(Integer id_obra) {
        this.id_obra = id_obra;
    }

    public String getNombre_obra() {
        return nombre_obra;
    }

    public void setNombre_obra(String nombre_obra) {
        this.nombre_obra = nombre_obra;
    }

    public String getDescripcion_obra() {
        return descripcion_obra;
    }

    public void setDescripcion_obra(String descripcion_obra) {
        this.descripcion_obra = descripcion_obra;
    }

    public Double getPrecio_obra() {
        return precio_obra;
    }

    public void setPrecio_obra(Double precio_obra) {
        this.precio_obra = precio_obra;
    }

    public Integer getEstado_obra() {
        return estado_obra;
    }

    public void setEstado_obra(Integer estado_obra) {
        this.estado_obra = estado_obra;
    }

    public String getNombre_artista() {
        return nombre_artista;
    }

    public void setNombre_artista(String nombre_artista) {
        this.nombre_artista = nombre_artista;
    }
}

