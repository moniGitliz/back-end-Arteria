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
    private  final  iUsuarioRepository usuarioRepository;


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

        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(()->
        new RuntimeException("Usuario no existe"));

        // 1. Crear la compra vacía
        Compra compra = new Compra();
        compra.setUsuario(usuario);

        // Inicializamos el total en cero antes de sumar
        compra.setValor_total_compra(BigDecimal.ZERO);

        // 2. Procesar cada obra

        for (Integer obraId : obraIds){
            Obra obra = obraRepository.findById(obraId)
                    .orElseThrow(() -> new RuntimeException("Obra no existe"));

            if (Boolean.FALSE.equals(obra.getEstado_obra())) {
                throw new RuntimeException("La obra '" + obra.getNombre_obra() + "' ya fue vendida.");
            }

            // 1. Crear el detalle y setear sus datos
            DetalleCompra det = new DetalleCompra();
            det.setObra(obra);
            det.setPrecio_unitario_obra(obra.getPrecio_obra());

            // 2. Vincular y actualizar total
            det.setCompra(compra);                              // FK lado hijo
            compra.getDetalles().add(det);                      // lista lado padre
            compra.setValor_total_compra(
                    compra.getValor_total_compra().add(det.getPrecio_unitario_obra()));

            // 3. Cambiar el estado de la obra
            obra.setEstado_obra(false);
            obraRepository.save(obra);
        }
        // 4. Finalmente guardamos la compra (cascade guardará los detalles)
        return compraRepository.save(compra);
    }

    @Override
    public void eliminarCompra(Integer id) {
        compraRepository.deleteById(id);
    }
}
