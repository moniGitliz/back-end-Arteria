package com.Arteria.ArteriaBackend.service;

import com.Arteria.ArteriaBackend.model.Compra;
import com.Arteria.ArteriaBackend.model.DetalleCompra;
import com.Arteria.ArteriaBackend.model.Obra;
import com.Arteria.ArteriaBackend.repository.iCompraRepository;
import com.Arteria.ArteriaBackend.repository.iDetalleCompraRepository;
import com.Arteria.ArteriaBackend.repository.iObraRepository;
import lombok.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DetalleCompraService  implements iDetalleCompraService{
    private final iDetalleCompraRepository detalleRepository;
    private final iCompraRepository compraRepo;   // para buscar la compra//private final iObraRepository obraRepo;     // para buscar la obra
    private final iObraRepository      obraRepo;     // para buscar la obra

    @Override
    public List<DetalleCompra> obtenerTodos() {
        return detalleRepository.findAll() ;
    }

    @Override
    public DetalleCompra obtenerPorId(Integer id) {
        return detalleRepository.findById(id).orElse(null);
    }

// Lógica de creación/eliminación corre desde CompraService
@Override
    public DetalleCompra crearDetalle(Integer compraId, Integer obraId) {
        Compra compra = compraRepo.findById(compraId)
                .orElseThrow(() -> new RuntimeException("Compra no existe con id: " + compraId));

        Obra obra = obraRepo.findById(obraId)
                .orElseThrow(() -> new RuntimeException("Obra no existe con id: " + obraId));

        if (!obra.getEstado_obra()) {
            throw new RuntimeException("La obra '" + obra.getNombre_obra() + "' ya fue vendida.");
        }

        DetalleCompra det = new DetalleCompra();
        det.setCompra(compra);
        det.setObra(obra);
        det.setPrecio_unitario_obra(obra.getPrecio_obra());
        detalleRepository.save(det);

        obra.setEstado_obra(false);
        obraRepo.save(obra);

        // 4) Actualizar el total en la compra: (suma el precioUnitario de este detalle al total actual)
        BigDecimal nuevoTotal = compra.getValor_total_compra().add(det.getPrecio_unitario_obra());
        compra.setValor_total_compra(nuevoTotal);

        // 5) Guardar la compra con el total actualizado:
        compraRepo.save(compra);

        return det; // como CascadeType.ALL en Compra persiste el detalle al guardar compra
    }



//    Segun la lógica del negocio un detalle de compra no se puede editar, pero igualmente aqui está el método
    @Override
    public DetalleCompra editarDetalle(Integer id, DetalleCompra detalleActualizado) {
        // 1. Buscamos el detalle existente
        DetalleCompra existente = detalleRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Detalle de compra no encontrado con el id: " + id)
                );

        // 2. Asignamos las relaciones directamente
        existente.setCompra(detalleActualizado.getCompra());       // Compra (objeto)
        existente.setObra(detalleActualizado.getObra());           // Obra (objeto)
        existente.setPrecio_unitario_obra(detalleActualizado.getPrecio_unitario_obra());

        // 3. Guardamos y devolvemos
        return detalleRepository.save(existente);
    }

    @Override
    public void eliminarDetalle(Integer id) {
        DetalleCompra det = detalleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Detalle no encontrado"));
        detalleRepository.delete(det);
    }


    @Override
    public List<DetalleCompra> obtenerPorCompraId(Integer compraId) {
        return detalleRepository.findByCompra_IdCompra(compraId);
    }

}
