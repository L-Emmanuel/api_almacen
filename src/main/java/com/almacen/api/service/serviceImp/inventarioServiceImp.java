package com.almacen.api.service.serviceImp;

import com.almacen.api.models.Inventario;
import com.almacen.api.payloads.Dto.InventarioDto;
import com.almacen.api.payloads.Dto.Pagination;
import com.almacen.api.service.inventarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class inventarioServiceImp implements inventarioService {


    @Override
    public Pagination<InventarioDto> findAll() {
        return null;
    }

    @Override
    public InventarioDto findById(int id) {
        return null;
    }

    @Override
    public InventarioDto save(Inventario inventario) {
        return null;
    }

    @Override
    public InventarioDto update(Inventario inventario) {
        return null;
    }
}
