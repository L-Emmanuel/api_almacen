package com.almacen.api.repository;

import com.almacen.api.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface productoRepository extends JpaRepository<Producto, Integer> {
}
