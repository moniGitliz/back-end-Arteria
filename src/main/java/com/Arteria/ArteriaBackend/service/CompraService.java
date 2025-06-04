package com.Arteria.ArteriaBackend.service;

import com.Arteria.ArteriaBackend.model.*;
import com.Arteria.ArteriaBackend.repository.*;

import lombok.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompraService implements iCompraService {

    private final iCompraRepository compraRepository;
    private final iDetalleCompraRepository detalleRepository;
    private final iObraRepository obraRepository;


    @Override
    public List<Compra> obtenerTodas() {
        return compraRepository.findAll();
    }

    @Override
    public Compra obtenerPorId(Integer id) {
        return compraRepository.findById(id).orElse(null);
    }

    @Override
    public Compra crearCompra(Integer usuarioId, List<Integer> obraIds) {
        // 1. Crear la compra vacía
        Compra compra = new Compra();
        compra.setUsuarioId(usuarioId);
        BigDecimal total = BigDecimal.ZERO;
        List<DetalleCompra> pendientes = new ArrayList<>();



        // 2. Procesar cada obra: ¡¡¡¡CONECTAR CUANDO TENGA TODO LO OBRA!!!!

        for (Integer obraId : obraIds){
            Obra obra = obraRepository.findById(obraId).orElseThrow(()->
                        new RuntimeException("Obra no existe"));
            // validar disponibilidad
            if(Boolean.FALSE.equals(obra.getEstado_obra())) {
                throw new RuntimeException("La obra '" + obra.getNombre_obra() + "' ya fue vendida.");
            }

            //Detalle de Compra
                DetalleCompra detalle = new DetalleCompra();
                detalle.setObraId(obraId);
                detalle.setPrecio_unitario_obra(obra.getPrecio_obra());
                pendientes.add(detalle);

                // actualizar estado de la obra
                obra.setEstado_obra(false);
                obraRepository.save(obra);

        total = total.add(obra.getPrecio_obra());

        }
        // 3. actualizar total y devolver
        compra.setValor_total_compra(total);
        compra = compraRepository.save(compra);      // ← único INSERT a compra

        // 3. Asignar id_compra a cada detalle y guardar
        for (DetalleCompra det : pendientes) {
            det.setCompraId(compra.getId_compra());
            detalleRepository.save(det);
        }

        return compra;
    }

    @Override
    public void eliminarCompra(Integer id) {
        compraRepository.deleteById(id);
    }
}
