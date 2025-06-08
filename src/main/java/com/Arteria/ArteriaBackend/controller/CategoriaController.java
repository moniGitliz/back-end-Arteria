package com.Arteria.ArteriaBackend.controller;

import com.Arteria.ArteriaBackend.model.Categoria;
import com.Arteria.ArteriaBackend.service.ICategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoriaController<id> {

    private final ICategoriaService categoriaService;

    @Autowired
    public CategoriaController(ICategoriaService categoriaService) {
        this.categoriaService = categoriaService;

    }
    @GetMapping
    public List<Categoria> ListCategoria(){
        return categoriaService.obtenerDatos();

    }
    @GetMapping("/{id}")
    public Categoria obtenerPorId(@PathVariable Integer id){
        return categoriaService.obtenerID(id);

    }
    @PostMapping("/crear")
    public ResponseEntity<String> guardarCategoria(@RequestBody Categoria categoria){
        categoriaService.guardarCategoria(categoria);
        return ResponseEntity.ok("Categoria agragada con exito");

    }
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<String> deleteCategria(@PathVariable Integer id){
        return ResponseEntity.ok("Categoria elimineda con exito");

    }
    @PostMapping("/editar/{id}")
    public ResponseEntity<String> editarCategoria(PathVariable Integer  , @RequestBody Categoria categoriaActualizada, Integer id){
        categoriaService.editarCategoria(id, categoriaActualizada);
        return ResponseEntity.ok("Categoria actualizada con exito");
    }

//    @GetMapping("/{id}/obras")
//    public ResponseEntity<?> listarObrasConCategoria(@PathVariable Integer id) {
//        Categoria categoria = categoriaService.obtenerCategoriaPorId(id);
//
//        if (categoria == null) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(categoria);
//    }

}
