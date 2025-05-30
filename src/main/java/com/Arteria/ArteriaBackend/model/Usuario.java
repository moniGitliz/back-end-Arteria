package com.Arteria.ArteriaBackend.model;


import jakarta.persistence.*;

@Entity
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


    public Usuario() {
    }

    public Usuario(int id_usuario, String nombre_usuario, String apellido_usuario, String telefono_usuario, String correo_usuario, String contrasenia_usuario) {
        this.id_usuario = id_usuario;
        this.nombre_usuario = nombre_usuario;
        this.apellido_usuario = apellido_usuario;
        this.telefono_usuario = telefono_usuario;
        this.correo_usuario = correo_usuario;
        this.contrasenia_usuario = contrasenia_usuario;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getApellido_usuario() {
        return apellido_usuario;
    }

    public void setApellido_usuario(String apellido_usuario) {
        this.apellido_usuario = apellido_usuario;
    }

    public String getTelefono_usuario() {
        return telefono_usuario;
    }

    public void setTelefono_usuario(String telefono_usuario) {
        this.telefono_usuario = telefono_usuario;
    }

    public String getCorreo_usuario() {
        return correo_usuario;
    }

    public void setCorreo_usuario(String correo_usuario) {
        this.correo_usuario = correo_usuario;
    }

    public String getContrasenia_usuario() {
        return contrasenia_usuario;
    }

    public void setContrasenia_usuario(String contrasenia_usuario) {
        this.contrasenia_usuario = contrasenia_usuario;
    }
}
