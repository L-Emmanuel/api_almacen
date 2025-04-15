package com.almacen.api.repository;

import com.almacen.api.models.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface proveedorRepository extends JpaRepository<Proveedor, Integer> {
}
