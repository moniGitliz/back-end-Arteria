package com.Arteria.ArteriaBackend.service;

import com.Arteria.ArteriaBackend.model.Categoria;
import com.Arteria.ArteriaBackend.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService implements ICategoriaService{
    @Autowired
    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public List<Categoria> obtenerDatos() {

        return categoriaRepository.findAll();
    }
    @Override
    public Categoria obtenerID(Integer ID){

        return categoriaRepository.findById(ID).orElse(null);
    }
    @Override
    public void guardarCategoria(Categoria categoria) {
        categoriaRepository.save(categoria);
    }
    @Override
    public void deletCategoria(Integer id){

        categoriaRepository.deleteById(id);
    }
    @Override
    public void editarCategoria(Integer id, Categoria categoriaActualizada){
        Categoria categoriaExistente = categoriaRepository.findById(id).orElse(null);

        if (categoriaExistente != null){
            categoriaExistente.setNombreCategoria(categoriaActualizada.getNombreCategoria());

            categoriaRepository.save(categoriaExistente);
        }else{
            throw new RuntimeException("Categoria no encontrada por el id: " + id);
        }
    }
}
