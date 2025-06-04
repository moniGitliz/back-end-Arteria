package com.Arteria.ArteriaBackend.controller;

import com.Arteria.ArteriaBackend.model.Imagenes;
import com.Arteria.ArteriaBackend.service.iImagenesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/imagenes")
public class ImagenesController {

    private final iImagenesService imagenesService;

    @Autowired
    public ImagenesController(iImagenesService imagenesService) { this.imagenesService = imagenesService; }

    @GetMapping
    public List<Imagenes> ListaImagenes(){ return imagenesService.obtenerTodos(); }

    @GetMapping ("/{id}")
    public Imagenes obtenerPorId (@PathVariable Integer id) { return imagenesService.obtenerPorId(id); }

    @PostMapping("/crear")
    public ResponseEntity<String> guardarImagenes(@RequestBody Imagenes imagenes) {
        imagenesService.guardarImagenes(imagenes);
        return ResponseEntity.ok("Imágenes agregadas con éxito");
    }
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<String> deleteImagenes(@PathVariable Integer id){
        imagenesService.deleteImagenes(id);
        return ResponseEntity.ok("Imagenes eliminadas con exito");
    }
    @PutMapping("/editar/{id}")
    public ResponseEntity<String> editarImagenes(@PathVariable Integer id, @RequestBody Imagenes imagenesActualizada){
        imagenesService.editarImagenes(id, imagenesActualizada);
        return ResponseEntity.ok("Imagenes actualizadas con exito");
    }
}
