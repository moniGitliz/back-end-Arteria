package com.Arteria.ArteriaBackend.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.util.*;



@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Table(name = "usuario")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
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

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonManagedReference // Para evitar el bucle infinito
    @JsonIgnore // ya no serializa compras desde Usuario, rompe el ciclo
    private List<Compra> compras = new ArrayList<>();



}
