package com.Arteria.ArteriaBackend.service;

import com.Arteria.ArteriaBackend.model.Usuario;
import com.Arteria.ArteriaBackend.repository.iUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService implements iUsuarioService {

    private final iUsuarioRepository usuarioRepository;


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
    public void guardarUsuario(Usuario usuario) {
    usuarioRepository.save(usuario);

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
            throw new RuntimeException("Usuario no encontrada con el id: " + id);
        }


    }
}
