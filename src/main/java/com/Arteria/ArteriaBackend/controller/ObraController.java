package com.Arteria.ArteriaBackend.controller;

import com.Arteria.ArteriaBackend.model.Obra;
import com.Arteria.ArteriaBackend.service.ObraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/obras")
public class ObraController {

    private final ObraService obraService;

    @Autowired
    public ObraController(ObraService obraService) {
        this.obraService = obraService;
    }

    @GetMapping //Traer todos los obras de la BD
    public List<Obra> listarObras(){
        return  obraService.obtenerTodos();
    }

    @GetMapping ("/{id}")  //Mapeando pero por id puerto 8080/obras/id(variable)
    public  Obra obtenerporId (@PathVariable Integer id){
        return obraService.obtenerPorId(id);
    }

    @PostMapping("/crear")
    public ResponseEntity<String> guardarObra(@RequestBody Obra obra){
        obraService.guardarobra(obra);
        return ResponseEntity.ok("Obra agregada con éxito!");
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<String> deleteObra (@PathVariable Integer id){
        obraService.deleteobra(id);
        return ResponseEntity.ok("Obra eliminada con éxito!");
    }

    @PutMapping ("/editar/{id}")
    public ResponseEntity<String> editarObra(@PathVariable Integer id, @RequestBody Obra obraActualizado){
        obraService.editarobra(id, obraActualizado);
        return ResponseEntity.ok("Obra actualizada con éxito!");
    }

    @GetMapping ("/byId/{id}") //Traer todas los obras por id
    public List<Obra> listarObrasById(@PathVariable Integer id){
        return  obraService.findObrasByCategoriaId(id);
    }
}
