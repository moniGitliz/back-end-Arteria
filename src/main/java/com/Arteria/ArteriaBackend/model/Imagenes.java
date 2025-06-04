package com.Arteria.ArteriaBackend.model;


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
    @Column(nullable = false, length = 100)
    private String imagen_principal_url;
    @Column(nullable = false, length = 100)
    private String miniatura_1_url;
    @Column(nullable = false, length = 100)
    private String miniatura_2_url;
    @Column(nullable = false, length = 100)
    private String miniatura_3_url;
}
