package com.Arteria.ArteriaBackend.service;

import com.Arteria.ArteriaBackend.model.Usuario;
import com.Arteria.ArteriaBackend.repository.iUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService implements iUsuarioService, UserDetailsService {


    private final iUsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    private static final String ADMIN_EMAIL = "arteriacol@gmail.com";


    @Override
    public List<Usuario> obtenerTodos() {

        return usuarioRepository.findAll();
    }

    @Override
    public Usuario obtenerPorId(Integer id) {
        return usuarioRepository.findById(id).orElse(null);
    }



    @Override
    public void registrarUsuario(Usuario usuario) {
        Usuario userLogin = new Usuario();
        userLogin.setNombre_usuario(usuario.getNombre_usuario());
        userLogin.setApellido_usuario(usuario.getApellido_usuario());
        userLogin.setTelefono_usuario(usuario.getTelefono_usuario());
        userLogin.setCorreo_usuario(usuario.getCorreo_usuario());
        // Encriptar la contraseña
        userLogin.setContrasenia_usuario(passwordEncoder.encode(usuario.getContrasenia_usuario()));

        usuarioRepository.save(userLogin);
    }

    @Override
    public void deleteUsuario(Integer id) {
    usuarioRepository.deleteById(id);
    }

    @Override
    public void editarUsuario(Integer id, Usuario usuarioActualizado) {
        //primero saber si existe
        Usuario usuarioExistente = usuarioRepository.findById(id).orElse(null);

        if(usuarioExistente != null){
            //Actualizar los campos del usuario existente
            usuarioExistente.setNombre_usuario(usuarioActualizado.getNombre_usuario());
            usuarioExistente.setApellido_usuario(usuarioActualizado.getApellido_usuario());
            usuarioExistente.setTelefono_usuario(usuarioActualizado.getTelefono_usuario());
            usuarioExistente.setCorreo_usuario(usuarioActualizado.getCorreo_usuario());
            // Encriptar la contraseña
            usuarioExistente.setContrasenia_usuario(passwordEncoder.encode(usuarioActualizado.getContrasenia_usuario()));

            //guardo al usuario actualizado
            usuarioRepository.save(usuarioExistente);

        } else {
            throw new RuntimeException("Usuario no encontrado con el id: " + id);
        }
    }

    // Método de carga de usuario implementado desde UserDetailsService
    @Override
    public UserDetails loadUserByUsername(String correoUsuario) throws UsernameNotFoundException {
        Usuario usuario= usuarioRepository.findByCorreo_usuario(correoUsuario);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        // Asignar el rol de ADMIN si el correo coincide
        if (ADMIN_EMAIL.equals(correoUsuario)) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN")); // ¡Importante! El prefijo ROLE_
        } else {
            authorities.add(new SimpleGrantedAuthority("ROLE_USER")); // Rol por defecto para otros usuarios
        }
        return new org.springframework.security.core.userdetails.User(
                usuario.getCorreo_usuario(),
                usuario.getContrasenia_usuario(),
                authorities);
    }

}
