package com.almacen.api.service.serviceImp;

import com.almacen.api.Exception.NoContentException;
import com.almacen.api.models.Producto;
import com.almacen.api.payloads.Dto.Pagination;
import com.almacen.api.payloads.Dto.ProductoDto;
import com.almacen.api.repository.productoRepository;
import com.almacen.api.service.productoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class productoServiceImp implements productoService {

    private final productoRepository productoRepo;

    private final ModelMapper modelMapper;


    @Override
    public Pagination<ProductoDto> findAll(int page, int size) {
        var result = productoRepo.findAll(PageRequest.of(page, size));

        if(result.isEmpty()){
            throw new NoContentException("No hay Datos que se puedan mostrar");
        }

        var productos = result
                .stream()
                .map(this::convertToDto)
                .toList();

        return Pagination
                .<ProductoDto>builder()
                .empty(result.isEmpty())
                .first(result.isFirst())
                .last(result.isLast())
                .last(result.isLast())
                .number(result.getNumber())
                .numberOfElement(result.getNumberOfElements())
                .size(result.getSize())
                .totalElements(result.getTotalElements())
                .totalPages(result.getTotalPages())
                .content(productos)
                .build();
    }

    @Override
    public ProductoDto findById(int id) {
        var movimientos = productoRepo.findById(id)
                .orElseThrow(() -> new NoContentException("No se encontro el ID"));

        return convertToDto(movimientos);
    }

    @Override
    public ProductoDto save(ProductoDto producto) {
        if(producto == null){
            throw new NoContentException("No se encontro producto a guardar");
        }
        var newProducto = convertToEntity(producto);

       return convertToDto(productoRepo.save(newProducto));
    }

    @Override
    public ProductoDto update(ProductoDto producto, int id) {
        var existProducto = productoRepo.findById(id)
                .orElseThrow(() -> new NoContentException("No se encontro el ID"));

        BeanUtils.copyProperties(producto, existProducto);

        productoRepo.save(existProducto);

        return producto;
    }


    private Producto convertToEntity(ProductoDto productoDto){
        return modelMapper.map(productoDto, Producto.class);
    }

    private ProductoDto convertToDto(Producto producto){
        return modelMapper.map(producto, ProductoDto.class);
    }

}
