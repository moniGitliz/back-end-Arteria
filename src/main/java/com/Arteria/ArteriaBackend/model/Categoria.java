package com.Arteria.ArteriaBackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria") //  clave para que coincida con tu base de datos
    private Integer idCategoria;

    @Column(name = "nombre_categoria") // Asegúrate de que este nombre también coincida
    private String nombreCategoria;

    /*-----------Relaciones---------*/
    //@OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, orphanRemoval = true) //orphanRemoval = si se elimina una obra de la lista, se borra de la base de datos
    //@JsonIgnoreProperties("categoria") // Para evitar el bucle infinito
    //private List<Obra> obras = new ArrayList<>();
    /*------------------------------*/

//Moni
//    /*-----------Relaciones---------*/
//    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, orphanRemoval = true) //orphanRemoval = si se elimina una obra de la lista, se borra de la base de datos
//    @JsonIgnore //Properties("categoria") // Para evitar el bucle infinito
//    private List<Obra> obras = new ArrayList<>();
//    /*------------------------------*/



    public Categoria() {
    }

    public Categoria(Integer idCategoria, String nombreCategoria) {
        this.idCategoria = idCategoria;
        this.nombreCategoria = nombreCategoria;
    }

//    public List<Obra> getObras() {
//        return obras;
//    }
//
//    public void setObras(List<Obra> obras) {
//        this.obras = obras;
//    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }
}
