package com.Arteria.ArteriaBackend.service;

import com.Arteria.ArteriaBackend.model.Imagenes;
import com.Arteria.ArteriaBackend.repository.iImagenesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImagenesService implements iImagenesService {

    private final iImagenesRepository imagenesRepository;

    @Autowired
    public ImagenesService(iImagenesRepository imagenesRepository) { this.imagenesRepository = imagenesRepository;}

    @Override
    public List<Imagenes> obtenerTodos() {
        return imagenesRepository.findAll();
    }

    @Override
    public Imagenes obtenerPorId(Integer id) {
        return imagenesRepository.findById(id).orElse(null);
    }

    @Override
    public void guardarImagenes(Imagenes imagenes) { imagenesRepository.save(imagenes); }

    @Override
    public void deleteImagenes(Integer id) { imagenesRepository.deleteById(id); }

    @Override
    public void editarImagenes(Integer id, Imagenes imagenesActualizada) {

        //primero saber si existe
        Imagenes imagenesExistente = imagenesRepository.findById(id).orElse(null);

        if(imagenesExistente != null){
            //Actualizar los campos de las imagenes existentes
            imagenesExistente.setImagen_principal_url(imagenesActualizada.getImagen_principal_url());
            imagenesExistente.setMiniatura_1_url(imagenesActualizada.getMiniatura_1_url());
            imagenesExistente.setMiniatura_2_url(imagenesActualizada.getMiniatura_2_url());
            imagenesExistente.setMiniatura_3_url(imagenesActualizada.getMiniatura_3_url());

            //guardo la obra actualizada
            imagenesRepository.save(imagenesExistente);

        } else {
            throw new RuntimeException("Imagen no encontrada con el id: " + id);
        }

    }
}
