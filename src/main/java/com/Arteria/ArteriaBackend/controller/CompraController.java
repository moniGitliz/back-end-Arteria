package com.Arteria.ArteriaBackend.controller;


import com.Arteria.ArteriaBackend.model.Compra;
import com.Arteria.ArteriaBackend.service.iCompraService;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/compras")
public class CompraController {
    private final iCompraService compraService;

    @Getter @Setter        // Lombok genera getters y setters
    private static class CompraRequest {
        private Integer usuarioId;
        private List<Integer> obraIds;
    }


    /* Crear compra   ¡¡¡¡CONECTAR CUANDO TENGA TODO LO OBRA!!!! */
    @PostMapping ("/crear")
    public ResponseEntity<Compra>crear(@RequestBody CompraRequest req) {
        Compra compra = compraService.crearCompra(req.getUsuarioId(), req.getObraIds());
        return ResponseEntity.ok(compra);
    }

    /* obtener una compra por su ID */
    @GetMapping("/{id}")
    public ResponseEntity<?> obtener(@PathVariable Integer id) {
        Compra c = compraService.obtenerPorId(id);
        if (c == null) {
            // si no existe, devolvemos 404

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La compra con id " + id + " no existe!");
        }
        // si existe, devolvemos 200 + cuerpo JSON
        return ResponseEntity.ok(c);
    }

    /* Listar todas las compras de la BD */
    @GetMapping
    public List<Compra> listar() { return compraService.obtenerTodas(); }



    /* Eliminar compra y sus detalles (por FK restrictiva) */
    @DeleteMapping ("/borrar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id){
        compraService.eliminarCompra(id);
        return ResponseEntity.ok("Compra eliminada con éxito!");
    }







}



