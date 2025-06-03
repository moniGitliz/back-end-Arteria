package com.Arteria.ArteriaBackend.service;

import com.Arteria.ArteriaBackend.model.DetalleCompra;

import java.util.List;

public interface iDetalleCompraService {
    List<DetalleCompra> obtenerTodos();
    DetalleCompra obtenerPorId(Integer id);
    DetalleCompra crearDetalle(DetalleCompra detalle);
    DetalleCompra editarDetalle(Integer id, DetalleCompra detalleActualizado);
    void eliminarDetalle(Integer id);

    /* opcional: listar por compra */
    List<DetalleCompra> obtenerPorCompraId(Integer compraId);

}
