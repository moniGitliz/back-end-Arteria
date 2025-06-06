package com.Arteria.ArteriaBackend.repository;

import com.Arteria.ArteriaBackend.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface iUsuarioRepository extends JpaRepository<Usuario, Integer> {
    Usuario  findByCorreo_usuario(String correo_usuario);
}
