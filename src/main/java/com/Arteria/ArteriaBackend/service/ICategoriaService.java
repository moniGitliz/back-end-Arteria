package com.Arteria.ArteriaBackend.service;

import com.Arteria.ArteriaBackend.model.Categoria;

import java.util.List;

public interface ICategoriaService {

    List<Categoria> obtenerDatos();
    Categoria obtenerID(Integer ID);
    void guardarCategoria(Categoria categoria);

    void deletCategoria(Integer id);

    void editarCategoria(Integer id, Categoria categoriaActualizada);

    //Categoria obtenerCategoriaPorId(Integer id);
}
