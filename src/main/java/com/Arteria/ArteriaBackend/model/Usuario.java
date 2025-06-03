package com.Arteria.ArteriaBackend.model;


import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Aqui se establece como un AUTOINCREMENT
    private Integer id_usuario;
    @Column(nullable = false, length = 50)
    private String nombre_usuario;
    @Column(nullable = false, length = 50)
    private String apellido_usuario;
    @Column(nullable = false, length = 20)
    private String telefono_usuario;
    @Column(nullable = false, length = 100)
    private String correo_usuario;
    @Column(nullable = false, length = 100)
    private String contrasenia_usuario;


}
