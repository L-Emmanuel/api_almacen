package com.almacen.api.service.serviceImp;

import com.almacen.api.Exception.NoContentException;
import com.almacen.api.models.Proveedor;
import com.almacen.api.payloads.Dto.Pagination;
import com.almacen.api.payloads.Dto.ProveedorDto;
import com.almacen.api.repository.proveedorRepository;
import com.almacen.api.service.proveedorService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class proveedorServiceImp implements proveedorService {

    private final proveedorRepository proveedorRepo;

    private final ModelMapper modelMapper;

    @Override
    public Pagination<ProveedorDto> findAll(int page, int size) {
        var result = proveedorRepo.findAll(PageRequest.of(page,size));

        if(result.isEmpty()){
            throw new NoContentException("No hay datos para mostrar");
        }

        var proveedores = result
                .stream()
                .map(this::convertToDto)
                .toList();

        return Pagination
                .<ProveedorDto>builder()
                .empty(result.isEmpty())
                .first(result.isFirst())
                .last(result.isLast())
                .last(result.isLast())
                .number(result.getNumber())
                .numberOfElement(result.getNumberOfElements())
                .size(result.getSize())
                .totalElements(result.getTotalElements())
                .totalPages(result.getTotalPages())
                .content(proveedores)
                .build();
    }

    @Override
    public ProveedorDto findById(int id) {
        var proveedores = proveedorRepo.findById(id)
                .orElseThrow(() -> new NoContentException("No se encontro el ID"));

        return convertToDto(proveedores);
    }

    @Override
    public ProveedorDto save(ProveedorDto proveedor) {
        if(proveedor == null){
            throw new NoContentException("No se tiene proveedor para guardar");
        }
        var newProveedor = convertToEntity(proveedor);

        return convertToDto(proveedorRepo.save(newProveedor));
    }

    @Override
    public ProveedorDto update(ProveedorDto proveedor, int id) {
        var existProveedor = proveedorRepo.findById(id)
                .orElseThrow(() -> new NoContentException("No se encotro el ID"));

        BeanUtils.copyProperties(proveedor, existProveedor);
        proveedorRepo.save(existProveedor);
        return proveedor;
    }


    private Proveedor convertToEntity(ProveedorDto proveedorDto){
        return modelMapper.map(proveedorDto, Proveedor.class);
    }

    private ProveedorDto convertToDto(Proveedor proveedor){
        return modelMapper.map(proveedor, ProveedorDto.class);
    }


}


