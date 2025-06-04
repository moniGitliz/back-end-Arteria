package com.Arteria.ArteriaBackend.repository;

import com.Arteria.ArteriaBackend.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface iCategoriaRepository extends JpaRepository <Categoria, Integer> {


}
