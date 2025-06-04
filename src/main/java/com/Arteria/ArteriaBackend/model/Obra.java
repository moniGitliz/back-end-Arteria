package com.Arteria.ArteriaBackend.model;

import jakarta.persistence.*;
import lombok.*;


import java.math.BigDecimal;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
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



}

