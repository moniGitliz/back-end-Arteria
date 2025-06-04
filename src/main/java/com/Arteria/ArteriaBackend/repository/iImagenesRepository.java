package com.Arteria.ArteriaBackend.repository;

import com.Arteria.ArteriaBackend.model.Imagenes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface iImagenesRepository extends JpaRepository<Imagenes, Integer> {
}
