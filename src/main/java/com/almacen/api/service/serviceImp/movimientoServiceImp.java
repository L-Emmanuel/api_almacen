package com.almacen.api.service.serviceImp;

import com.almacen.api.Exception.NoContentException;
import com.almacen.api.models.Movimiento;
import com.almacen.api.models.Producto;
import com.almacen.api.payloads.Dto.MovimientoDto;
import com.almacen.api.payloads.Dto.Pagination;
import com.almacen.api.payloads.Dto.ProductoDto;
import com.almacen.api.repository.movimientoRepository;
import com.almacen.api.repository.productoRepository;
import com.almacen.api.service.movimientoService;
import com.almacen.api.utils.ENUMS.tipo_movimiento;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class movimientoServiceImp implements movimientoService {

    private final movimientoRepository movimientoRepo;

    private final productoRepository productoRepo;

    private final ModelMapper modelMapper;

    @Override
    public Pagination<MovimientoDto> findAll(int page, int size) {
        var result = movimientoRepo.findAll(PageRequest.of(page, size));

        if (result.isEmpty()){
            throw new NoContentException("No hay Datos que se puedan mostrar");
        }

        var movimientos = result
                .stream()
                .map(this::convertToDto)
                .toList();

        return Pagination
                .<MovimientoDto>builder()
                .empty(result.isEmpty())
                .first(result.isFirst())
                .last(result.isLast())
                .last(result.isLast())
                .number(result.getNumber())
                .numberOfElement(result.getNumberOfElements())
                .size(result.getSize())
                .totalElements(result.getTotalElements())
                .totalPages(result.getTotalPages())
                .content(movimientos)
                .build();
    }

    @Override
    public MovimientoDto findById(int id) {
        var movimientos = movimientoRepo.findById(id)
                .orElseThrow(() -> new NoContentException("No se encuentra el ID"));

        return convertToDto(movimientos);
    }

    @Override
    public MovimientoDto save(MovimientoDto movimiento) {
        if(movimiento == null){
            throw new NoContentException("No hay movimientos que se guarden");
        }
        var newMovimiento = convertToEntity(movimiento);

        var producto = productoRepo.findById(movimiento.getProducto().getId_producto())
                .orElseThrow(() -> new NoContentException("No se encontro el producto con ese ID"));

        if(movimiento.getTipo_movimiento() == tipo_movimiento.ENTRADA || movimiento.getTipo_movimiento() == tipo_movimiento.AJUSTE){
            int nuevaCantidad = producto.getCantidad_disponible() + movimiento.getCantidad();
            producto.setCantidad_disponible(nuevaCantidad);
            productoRepo.save(producto);
        } else if (movimiento.getTipo_movimiento() == tipo_movimiento.SALIDA) {
            int nuevaCantidad = producto.getCantidad_disponible() - movimiento.getCantidad();
            if(nuevaCantidad < 0){
                throw new NoContentException("No hay la cantidad suficiente para sacar");
            }
            producto.setCantidad_disponible(nuevaCantidad);
            productoRepo.save(producto);
        }
        newMovimiento.setProducto(producto);

        return convertToDto(movimientoRepo.save(newMovimiento));
    }

    @Override
    public MovimientoDto update(MovimientoDto movimiento, int id) {
        var existMovimeinto = movimientoRepo.findById(id)
                .orElseThrow(() -> new NoContentException("No existe el ID"));

        BeanUtils.copyProperties(movimiento, existMovimeinto);
        movimientoRepo.save(existMovimeinto);
        return movimiento;
    }



    private Movimiento convertToEntity(MovimientoDto movimientoDto){
        return modelMapper.map(movimientoDto, Movimiento.class);
    }

    private MovimientoDto convertToDto(Movimiento movimiento){
        return modelMapper.map(movimiento, MovimientoDto.class);
    }
}
