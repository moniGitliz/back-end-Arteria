package com.Arteria.ArteriaBackend.repository;

import com.Arteria.ArteriaBackend.model.Compra;
import com.Arteria.ArteriaBackend.model.DetalleCompra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface iDetalleCompraRepository extends JpaRepository<DetalleCompra, Integer> {
    List<DetalleCompra> findByCompraId(Integer compraId);
}
