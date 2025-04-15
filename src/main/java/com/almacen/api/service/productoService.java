package com.almacen.api.service;


import com.almacen.api.payloads.Dto.Pagination;
import com.almacen.api.payloads.Dto.ProductoDto;

import java.util.List;

public interface productoService {
    Pagination<ProductoDto> findAll();
    ProductoDto findById(int id);
    ProductoDto save(ProductoDto producto);
    ProductoDto uodate(ProductoDto producto, int id);

}
