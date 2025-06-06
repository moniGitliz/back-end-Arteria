package com.Arteria.ArteriaBackend.controller;

import com.Arteria.ArteriaBackend.config.JwtUtil;
import com.Arteria.ArteriaBackend.model.Usuario;
import com.Arteria.ArteriaBackend.service.UsuarioService;
import com.Arteria.ArteriaBackend.service.iUsuarioService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://127.0.0.1:5501") // Esta es mi ruta, se ve en live Server
@RestController
@RequestMapping ("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final iUsuarioService usuarioService;

    private final AuthenticationManager authenticationManager; // Para el proceso de login
    private final UsuarioService userDetailsService; // Tu UsuarioService actúa como UserDetailsService
    private final JwtUtil jwtUtil; // Para crear tokens JWT
    private final PasswordEncoder passwordEncoder; // Para cifrar y verificar contraseñas


    @GetMapping //Traer todos los usuarios de la BD
    public List<Usuario> listarUsuarios(){
        return  usuarioService.obtenerTodos();
    }

    @GetMapping ("/{id}")  //Mapeando pero por id puerto 8080/usuarios/id(variable)
    public  Usuario obtenerporId (@PathVariable Integer id){
        return usuarioService.obtenerPorId(id);
    }

    // Endpoint para registrar un nuevo usuario (público, accesible para todos)
    @PostMapping("/registro")
    public ResponseEntity<String> registrarUsuario(@RequestBody Usuario usuario) {
        usuarioService.registrarUsuario(usuario); // Llama al método que cifra y guarda el usuario
        return ResponseEntity.ok("Usuario registrado con éxito");
    }


    // Endpoint para el inicio de sesión (público, accesible para todos)
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario user) { // Recibe un objeto Usuario con correo y contraseña
        try {
            // Autentica al usuario usando el AuthenticationManager
            // Esto dispara la llamada a UsuarioService.loadUserByUsername y la verificación de contraseña
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getCorreoUsuario(), user.getContrasenia_usuario())

            );
        } catch (Exception e) {
            // Si la autenticación falla (ej. credenciales inválidas), retorna un error 401
            return ResponseEntity.status(401).body("Credenciales inválidas");
        }

        // Si la autenticación es exitosa, carga los detalles del usuario
        final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getCorreoUsuario());
        // Genera el token JWT para el usuario autenticado
        final String token = jwtUtil.generateToken(userDetails.getUsername()); // userDetails.getUsername() devuelve el correo

        // Retorna el token JWT en la respuesta
        return ResponseEntity.ok(token);
    }


//    //Pruebas de login sin JWT
//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody Usuario usuario) {
//        for (Usuario usuarioGuardado : usuarioService.obtenerTodos()) {
//            if (usuarioGuardado.getCorreo_usuario().equals(usuario.getCorreo_usuario()) && usuarioGuardado.getContrasenia_usuario().equals(usuario.getContrasenia_usuario())) {
//                return ResponseEntity.ok("Inicio de sesión exitoso");
//            }
//        }
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
//    }



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

    private static class AuthResponse {
        private final String jwt;

        public AuthResponse(String jwt) {
            this.jwt = jwt;
        }

        public String getJwt() {
            return jwt;
        }
    }

}
