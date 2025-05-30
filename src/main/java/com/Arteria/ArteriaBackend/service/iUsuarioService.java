package com.Arteria.ArteriaBackend.service;

import com.Arteria.ArteriaBackend.model.Usuario;

import java.util.List;

public interface iUsuarioService {
    List<Usuario> obtenerTodos();
    Usuario obtenerPorId(Integer id);
    void guardarUsuario(Usuario usuario);
    void deleteUsuario(Integer id);
    void editarUsuario (Integer id, Usuario usuarioActualizado);
}
