package com.Arteria.ArteriaBackend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;


import java.math.BigDecimal;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Obra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Aqui se establece como un AUTOINCREMENT
    private Integer id_obra; //PrimaryKey
    @Column(nullable = false, length = 50)
    private String nombre_obra;
    @Column(nullable = false, length = 150)
    private String descripcion_obra;
    @Column(nullable = false,  precision = 10, scale = 2)
    private BigDecimal precio_obra;
    @Column(nullable = false)
    private Boolean estado_obra;
    @Column(nullable = false, length = 50)
    private String nombre_artista;


    /*-----------Relaciones---------*/
    @ManyToOne
    @JoinColumn(name = "FK_id_categoria") //Va FK???
    private Categoria categoria;
    /*------------------------------*/

    /*----------- Relación con Imagenes -----------*/
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_id_imagenes")//, referencedColumnName = "id_imagen")
    @JsonManagedReference
    private Imagenes imagenes;


    //Moni
//
//    /*-----------Relaciones---------*/
//    @ManyToOne
//    @JoinColumn(name = "FK_id_categoria") //Va FK???
//    private Categoria categoria;
//
//
//
//    /*----------- Relación con Imagenes -----------*/
//    // Obra es el lado "inverso" de la relación, la cual es "mapeada por" el campo "obra" en la clase Imagenes.
//    @OneToOne(mappedBy = "obra", cascade = CascadeType.ALL)
//    private Imagenes imagenes;








}

