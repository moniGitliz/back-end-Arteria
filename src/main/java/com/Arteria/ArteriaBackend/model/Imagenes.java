package com.Arteria.ArteriaBackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Imagenes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Aqui se establece como un AUTOINCREMENT
    private Integer id_imagenes;
    @Column(nullable = false, length = 255)
    private String imagen_principal_url;
    @Column(nullable = false, length = 255)
    private String miniatura_1_url;
    @Column(nullable = false, length = 255)
    private String miniatura_2_url;
    @Column(nullable = false, length = 255)
    private String miniatura_3_url;

    /*----------- Relación con Obra -----------*/
    @OneToOne(mappedBy = "imagenes")
    @JsonBackReference
    private Obra obra;
}
