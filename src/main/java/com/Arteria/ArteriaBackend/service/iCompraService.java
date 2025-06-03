package com.Arteria.ArteriaBackend.service;

import com.Arteria.ArteriaBackend.model.Compra;

import java.util.List;

public interface iCompraService {
    /** Crea una compra a partir del usuario y la lista de obras. */
    Compra crearCompra(Integer usuarioId, List<Integer> obraIds);
    Compra obtenerPorId(Integer id);
    List<Compra> obtenerTodas();
    void eliminarCompra(Integer id);
}
