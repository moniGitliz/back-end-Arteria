package com.Arteria.ArteriaBackend.service;

import com.Arteria.ArteriaBackend.model.DetalleCompra;

import java.util.List;

public interface iDetalleCompraService {
    List<DetalleCompra> obtenerTodos();
    DetalleCompra obtenerPorId(Integer id);


     //Lógica de creación/eliminación corre desde CompraService
    DetalleCompra crearDetalle(Integer compraId, Integer obraId);

     //Segun la lógica del negocio un detalle de compra no se puede editar, pero igualmente aqui está el método
    DetalleCompra editarDetalle(Integer id, DetalleCompra detalleActualizado);


    void eliminarDetalle(Integer id);

    /* opcional: listar por compra */
    List<DetalleCompra> obtenerPorCompraId(Integer compraId);

}
