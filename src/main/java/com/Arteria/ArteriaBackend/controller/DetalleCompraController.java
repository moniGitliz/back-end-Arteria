package com.Arteria.ArteriaBackend.controller;
import com.Arteria.ArteriaBackend.model.DetalleCompra;
import com.Arteria.ArteriaBackend.service.iDetalleCompraService;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/detalles")
public class DetalleCompraController {
    private final iDetalleCompraService detalleCompraService;

    @Getter @Setter @NoArgsConstructor
    private static class DetalleCreateDTO {
        private Integer compraId;
        private Integer obraId;
    }


    @GetMapping //Traer todos los detalles(GET /detalles) o por compraId (GET /detalles?compraId={idCompra})
    public List<DetalleCompra> listar(@RequestParam(required = false) Integer compraId) {
            return (compraId != null)
                    ? detalleCompraService.obtenerPorCompraId(compraId)
                    : detalleCompraService.obtenerTodos();
        }

    /* Obtener detalle de compra por ID */
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Integer id) {
        DetalleCompra d = detalleCompraService.obtenerPorId(id);
        if (d == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El detalle de compra con id " + id + " no existe!");
        }
        // si existe, devolvemos 200 + cuerpo JSON
        return ResponseEntity.ok(d);
    }

    /* Crear */
    @PostMapping ("/crear")
    public ResponseEntity<String> crear(@RequestBody DetalleCreateDTO dto) {
        detalleCompraService.crearDetalle(dto.getCompraId(), dto.getObraId());
        return ResponseEntity.ok("Detalle de compra agregado con éxito!");
    }

    /* Editar */
    @PutMapping("/editar/{id}")
    public ResponseEntity<String> editarDetalle(@PathVariable Integer id, @RequestBody DetalleCompra detalleActualizado) {
        detalleCompraService.editarDetalle(id, detalleActualizado);
        return ResponseEntity.ok("Detalle de compra actualizado con éxito!");
    }


    /* Eliminar */
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        detalleCompraService.eliminarDetalle(id);
        return ResponseEntity.ok("Detalle de compra eliminado con éxito!");
    }
















}
