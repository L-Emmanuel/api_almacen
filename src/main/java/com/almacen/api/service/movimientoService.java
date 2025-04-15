package com.almacen.api.service;

import com.almacen.api.models.Movimiento;
import com.almacen.api.payloads.Dto.MovimientoDto;
import com.almacen.api.payloads.Dto.Pagination;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface movimientoService{
    Pagination<MovimientoDto> findAll();
    MovimientoDto findById(int id);
    MovimientoDto save(Movimiento movimiento);
    MovimientoDto update(Movimiento movimiento, int id);

}
