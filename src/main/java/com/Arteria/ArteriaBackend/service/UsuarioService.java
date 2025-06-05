package com.Arteria.ArteriaBackend.service;

import com.Arteria.ArteriaBackend.model.Usuario;
import com.Arteria.ArteriaBackend.repository.iUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Service
public class UsuarioService implements iUsuarioService {


    private final iUsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


@Autowired
    public UsuarioService(iUsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }




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
        userLogin.setContrasenia_usuario(passwordEncoder.encode(usuario.getContrasenia_usuario()));
        // Encriptar la contraseña
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
            usuarioExistente.setCorreo_usuario(usuarioActualizado.getCorreo_usuario()   );
            usuarioExistente.setContrasenia_usuario(usuarioActualizado.getContrasenia_usuario());

            //guardo al usuario actualizado
            usuarioRepository.save(usuarioExistente);

        } else {
            throw new RuntimeException("Usuario no encontrado con el id: " + id);
        }


    }
}
