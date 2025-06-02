package com.Arteria.ArteriaBackend.service;

import com.Arteria.ArteriaBackend.model.Categoria;
import com.Arteria.ArteriaBackend.repository.iCategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService implements ICategoriaService{
    @Autowired
    private final iCategoriaRepository CategoriaRepository;

    public CategoriaService(iCategoriaRepository iCategoriaRepository) {
        this.CategoriaRepository = iCategoriaRepository;
    }

    @Override
    public List<Categoria> obtenerDatos() {

        return CategoriaRepository.findAll();
    }
    @Override
    public Categoria obtenerID(Integer ID){

        return CategoriaRepository.findById(ID).orElse(null);
    }
    @Override
    public void guardarCategoria(Categoria categoria) {
        CategoriaRepository.save(categoria);
    }
    @Override
    public void deletCategoria(Integer id){

        CategoriaRepository.deleteById(id);
    }
    @Override
    public void editarCategoria(Integer id, Categoria categoriaActualizada){
        Categoria categoriaExistente = CategoriaRepository.findById(id).orElse(null);

        if (categoriaExistente != null){
            categoriaExistente.setNombreCategoria(categoriaActualizada.getNombreCategoria());

            CategoriaRepository.save(categoriaExistente);
        }else{
            throw new RuntimeException("Categoria no encontrada por el id: " + id);
        }
    }
}
