package com.Arteria.ArteriaBackend.service;

import com.Arteria.ArteriaBackend.model.Imagenes;

import java.util.List;

public interface iImagenesService {
    List<Imagenes> obtenerTodos();
    Imagenes obtenerPorId(Integer id);
    void guardarImagenes(Imagenes imagenes);
    void deleteImagenes(Integer id);
    void editarImagenes(Integer id, Imagenes imagenesActualizada);
}
