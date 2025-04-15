package com.almacen.api.repository;

import com.almacen.api.models.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface movimientoRepository extends JpaRepository<Movimiento, Integer> {
}
