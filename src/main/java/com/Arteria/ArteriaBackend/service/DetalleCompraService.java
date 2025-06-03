package com.Arteria.ArteriaBackend.service;

import com.Arteria.ArteriaBackend.model.DetalleCompra;
import com.Arteria.ArteriaBackend.repository.iDetalleCompraRepository;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DetalleCompraService  implements iDetalleCompraService{
    private final iDetalleCompraRepository detalleRepository;


    @Override
    public List<DetalleCompra> obtenerTodos() {
        return detalleRepository.findAll() ;
    }

    @Override
    public DetalleCompra obtenerPorId(Integer id) {
        return detalleRepository.findById(id).orElse(null);
    }

    @Override
    public DetalleCompra crearDetalle(DetalleCompra detalle) {
        return detalleRepository.save(detalle);
    }

    @Override
    public DetalleCompra editarDetalle(Integer id, DetalleCompra detalleActualizado) {
        DetalleCompra existente = detalleRepository.findById(id).orElse(null);

        if(existente != null){
            existente.setCompraId(detalleActualizado.getCompraId());
            existente.setObraId(detalleActualizado.getObraId());
            existente.setPrecio_unitario_obra(detalleActualizado.getPrecio_unitario_obra());
        return detalleRepository.save(existente);
        } else {
            throw new RuntimeException("Detalle de compra no encontrado con el id: " + id);
        }
    }

    @Override
    public void eliminarDetalle(Integer id) {
        detalleRepository.deleteById(id);
    }


    @Override
    public List<DetalleCompra> obtenerPorCompraId(Integer compraId) {
        return detalleRepository.findByCompraId(compraId);
    }
}
