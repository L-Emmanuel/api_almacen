package com.almacen.api.repository;

import com.almacen.api.models.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface inventarioRepository extends JpaRepository<Inventario, Integer> {
}
