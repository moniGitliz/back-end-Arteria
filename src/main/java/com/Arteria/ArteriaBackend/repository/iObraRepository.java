package com.Arteria.ArteriaBackend.repository;

import com.Arteria.ArteriaBackend.model.Obra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface iObraRepository extends JpaRepository<Obra, Integer> {


        @Query("SELECT o FROM Obra o WHERE o.categoria.idCategoria = :idCategoria")
        List<Obra> findObrasByCategoriaId(@Param("idCategoria") Integer idCategoria);


}
