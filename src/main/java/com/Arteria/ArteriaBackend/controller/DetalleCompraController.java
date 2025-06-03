package com.Arteria.ArteriaBackend.controller;
import com.Arteria.ArteriaBackend.model.DetalleCompra;
import com.Arteria.ArteriaBackend.service.iDetalleCompraService;

import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/detalles")
public class DetalleCompraController {
    private final iDetalleCompraService detalleCompraService;

    @GetMapping //Traer todos los detalles(GET /detalles) o por compraId (GET /detalles?compraId={idCompra})
    public List<DetalleCompra> listar(@RequestParam(required = false) Integer compraId) {
            return (compraId != null)
                    ? detalleCompraService.obtenerPorCompraId(compraId)
                    : detalleCompraService.obtenerTodos();
        }

    /* Obtener detalle de compra por ID */
    @GetMapping("/{id}")
    public DetalleCompra obtenerPorId(@PathVariable Integer id) {
        return detalleCompraService.obtenerPorId(id);
    }

    /* Crear */
    @PostMapping ("/crear")
    public ResponseEntity<String> crear(@RequestBody DetalleCompra detalle) {
        detalleCompraService.crearDetalle(detalle);
        return ResponseEntity.ok("Detalle de compra agregado con éxito!");
    }


    /* Eliminar */
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        detalleCompraService.eliminarDetalle(id);
        return ResponseEntity.ok("Detalle de compra eliminado con éxito!");
    }

    /* Editar */
    @PutMapping("/editar/{id}")
    public ResponseEntity<String> editarDetalle(@PathVariable Integer id, @RequestBody DetalleCompra detalleActualizado) {
        detalleCompraService.editarDetalle(id, detalleActualizado);
        return ResponseEntity.ok("Detalle de compra actualizado con éxito!");
    }















}
