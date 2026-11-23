package com.bodegas.repository;

import com.bodegas.entity.Bodega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BodegaRepository extends JpaRepository<Bodega, Long> {

    @Query("select distinct b from Bodega b left join fetch b.productos order by b.id")
    List<Bodega> findAllConProductos();
}
