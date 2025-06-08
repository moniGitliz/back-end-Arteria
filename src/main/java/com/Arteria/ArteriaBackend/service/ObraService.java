package com.Arteria.ArteriaBackend.service;

import com.Arteria.ArteriaBackend.model.Categoria;
import com.Arteria.ArteriaBackend.model.Obra;
import com.Arteria.ArteriaBackend.model.Obra;
import com.Arteria.ArteriaBackend.repository.iCategoriaRepository;
import com.Arteria.ArteriaBackend.repository.iObraRepository;
import com.Arteria.ArteriaBackend.repository.iObraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObraService implements iObraService {

    private final iObraRepository obraRepository;
    private final iCategoriaRepository categoriaRepository;

    @Autowired
    public ObraService(iObraRepository obraRepository, iCategoriaRepository categoriaRepository) {
        this.obraRepository = obraRepository;
        this.categoriaRepository = categoriaRepository;
    }


    @Override
    public List<Obra> obtenerTodos() {
        return obraRepository.findAll();
    }

    @Override
    public Obra obtenerPorId(Integer id) {
        return obraRepository.findById(id).orElse(null);
    }

    @Override
    public void guardarobra(Obra obra) {
        obraRepository.save(obra);
    }

    @Override
    public void deleteobra(Integer id) {
        obraRepository.deleteById(id);
    }

    @Override
    public void editarobra(Integer id, Obra obraActualizada) {

        //primero saber si existe
        Obra obraExistente = obraRepository.findById(id).orElse(null);

        if(obraExistente != null){
            //Actualizar los campos del obra existente
            obraExistente.setNombre_obra(obraActualizada.getNombre_obra());
            obraExistente.setDescripcion_obra(obraActualizada.getDescripcion_obra());
            obraExistente.setPrecio_obra(obraActualizada.getPrecio_obra());
            obraExistente.setEstado_obra(obraActualizada.getEstado_obra());
            obraExistente.setNombre_artista(obraActualizada.getNombre_artista());
           // obraExistente.setCategoria(obraActualizada.getCategoria());

            // En caso de que se necesite editar la categoría
            Categoria categoriaCompleta = categoriaRepository.findById(obraActualizada.getCategoria().getIdCategoria())
                    .orElse(null);
            obraExistente.setCategoria(categoriaCompleta);


            //guardo la obra actualizada
            obraRepository.save(obraExistente);

        } else {
            throw new RuntimeException("Obra no encontrada con el id: " + id);
        }
        
    }

    @Override
    public List<Obra> findObrasByCategoriaId(Integer idCategoria) {
        return obraRepository.findObrasByCategoriaId(idCategoria);
    }
}
