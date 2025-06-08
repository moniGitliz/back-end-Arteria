package com.Arteria.ArteriaBackend.service;

import com.Arteria.ArteriaBackend.model.Obra;
import com.Arteria.ArteriaBackend.model.Usuario;

import java.util.List;

public interface iObraService {
    List<Obra> obtenerTodos();
    Obra obtenerPorId(Integer id);
    void guardarobra(Obra obra);
    void deleteobra(Integer id);
    void editarobra (Integer id, Obra obraActualizada);

    List<Obra> findObrasByCategoriaId(Integer idCategoria);
/*
    // --- Método para Barra de búsqueda --//
    List<Obra> buscarObras(String termino);

 */
}
