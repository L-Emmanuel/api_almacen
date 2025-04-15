package com.almacen.api.service;

import com.almacen.api.models.Proveedor;
import com.almacen.api.payloads.Dto.Pagination;
import com.almacen.api.payloads.Dto.ProveedorDto;

import java.util.List;

public interface proveedorService {
    Pagination<ProveedorDto> findAll();
    ProveedorDto findById(int id);
    ProveedorDto save(ProveedorDto proveedor);
    ProveedorDto update(ProveedorDto proveedor, int id);
}
