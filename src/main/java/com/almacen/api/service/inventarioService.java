package com.almacen.api.service;

import com.almacen.api.models.Inventario;
import com.almacen.api.payloads.Dto.InventarioDto;
import com.almacen.api.payloads.Dto.Pagination;

import java.util.List;
import java.util.Optional;

public interface inventarioService {
    Pagination<InventarioDto> findAll(int page, int size);
    InventarioDto findById(int id);
    InventarioDto save(InventarioDto inventario);
    InventarioDto update(int id,InventarioDto inventario);

}
