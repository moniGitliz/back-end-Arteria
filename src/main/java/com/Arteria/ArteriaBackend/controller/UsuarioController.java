package com.Arteria.ArteriaBackend.controller;

import com.Arteria.ArteriaBackend.model.Usuario;
import com.Arteria.ArteriaBackend.service.iUsuarioService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://127.0.0.1:5501") // Esta es mi ruta, se ve en live Server
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



    //Pruebas de Registro de usuario
    @PostMapping("/registro")
    public ResponseEntity<?> registrarUsuario(@RequestBody Usuario usuario) {
        usuarioService.guardarUsuario(usuario);
        return ResponseEntity.ok("Usuario registrado con éxito");
    }


    //Pruebas de login
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Usuario usuario) {
        for (Usuario usuarioGuardado : usuarioService.obtenerTodos()) {
            if (usuarioGuardado.getCorreo_usuario().equals(usuario.getCorreo_usuario()) && usuarioGuardado.getContrasenia_usuario().equals(usuario.getContrasenia_usuario())) {
                return ResponseEntity.ok("Inicio de sesión exitoso");
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
    }



}
