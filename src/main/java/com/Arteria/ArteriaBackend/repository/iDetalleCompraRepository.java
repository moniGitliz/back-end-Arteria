package com.Arteria.ArteriaBackend.repository;

import com.Arteria.ArteriaBackend.model.DetalleCompra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface iDetalleCompraRepository extends JpaRepository<DetalleCompra, Integer> {
    List<DetalleCompra> findByCompra_IdCompra(Integer compraId);

//    Si usas edición/autonomía: agrega también, por ejemplo,
//    Optional<DetalleCompra> findByObra_Id(Integer obraId);
}
