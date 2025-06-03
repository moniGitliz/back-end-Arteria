package com.Arteria.ArteriaBackend.repository;

import com.Arteria.ArteriaBackend.model.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface iCompraRepository extends JpaRepository<Compra, Integer> {

}
