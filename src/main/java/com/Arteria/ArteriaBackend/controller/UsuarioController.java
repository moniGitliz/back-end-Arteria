package com.Arteria.ArteriaBackend.controller;

import com.Arteria.ArteriaBackend.model.Usuario;
import com.Arteria.ArteriaBackend.service.iUsuarioService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final iUsuarioService usuarioService;


    @GetMapping //Traer todos los usuarios de la BD
    public List<Usuario> listarUsuarios(){
    return  usuarioService.obtenerTodos();
    }

    @GetMapping ("/{id}")  //Mapeando pero por id puerto 8080/usuarios/id(variable)
    public  Usuario obtenerporId (@PathVariable Integer id){
    return usuarioService.obtenerPorId(id);
    }

    @PostMapping ("/crear")
    public ResponseEntity<String> guardarUsuario(@RequestBody Usuario usuario){
    usuarioService.guardarUsuario(usuario);
    return ResponseEntity.ok("Usuario agregado con éxito!");
    }

    @DeleteMapping ("/borrar/{id}")
    public ResponseEntity<String> deleteUsuario (@PathVariable Integer id){
    usuarioService.deleteUsuario(id);
        return ResponseEntity.ok("Usuario eliminado con éxito!");
    }

    @PutMapping ("/editar/{id}")
    public ResponseEntity<String> editarUsuario(@PathVariable Integer id, @RequestBody Usuario usuarioActualizado){
    usuarioService.editarUsuario(id, usuarioActualizado);
    return ResponseEntity.ok("Persona actualizada con éxito!");
    }

}
