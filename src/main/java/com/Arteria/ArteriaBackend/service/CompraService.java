package com.Arteria.ArteriaBackend.service;

import com.Arteria.ArteriaBackend.model.Compra;
import com.Arteria.ArteriaBackend.model.DetalleCompra;
import com.Arteria.ArteriaBackend.repository.iCompraRepository;
import com.Arteria.ArteriaBackend.repository.iDetalleCompraRepository;
import lombok.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompraService implements iCompraService {

    private final iCompraRepository compraRepository;
    private final iDetalleCompraRepository detalleRepository;
    //private final iObraRepository  obraRepository;


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
        compra = compraRepository.save(compra); // Aqui se genera el id

        BigDecimal total = BigDecimal.ZERO;

        // 2. Procesar cada obra: ¡¡¡¡CONECTAR CUANDO TENGA TODO LO OBRA!!!!

        for (Integer obraId : obraIds){
//          Obra obra = obraRepositoryfindById(obraId).orElseThrow(()->
//                        new RuntimeException("Obra no existe"));
//            // validar disponibilidad
//            if (Boolean.FALSE.equals(obra.getEstado_obra())) {
//            } else {
//                throw new RuntimeException("La obra '" + obra.getNombre() + "' ya fue vendida.");
//            }

            //Detalle de Compra
                DetalleCompra detalle = new DetalleCompra();
                detalle.setCompraId(compra.getId_compra());
//                detalle.setObraId(obraId);
//                detalle.setPrecio_unitario_obra(obra.getPrecio());
                detalleRepository.save(detalle);

                // actualizar estado de la obra
//                obra.setEstado_obra(false);
//                obraRepo.save(obra);

//        total = total.add(obra.getPrecio_obra());

        }
        // 3. actualizar total y devolver
        compra.setValor_total_compra(total);
        return compraRepository.save(compra);
    }

    @Override
    public void eliminarCompra(Integer id) {
        compraRepository.deleteById(id);
    }
}
