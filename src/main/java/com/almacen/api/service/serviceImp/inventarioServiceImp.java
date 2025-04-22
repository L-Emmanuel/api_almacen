package com.almacen.api.service.serviceImp;

import com.almacen.api.Exception.NoContentException;
import com.almacen.api.models.Inventario;
import com.almacen.api.payloads.Dto.InventarioDto;
import com.almacen.api.payloads.Dto.Pagination;
import com.almacen.api.repository.inventarioRepository;
import com.almacen.api.service.inventarioService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class inventarioServiceImp implements inventarioService {

    private final inventarioRepository inventarioRepo;

    private final ModelMapper modelMapper;

    @Transactional(readOnly = true)
    @Override
    public Pagination<InventarioDto> findAll(int page, int size) {

        var result = inventarioRepo.findAll(PageRequest.of(page, size));

        if(result.isEmpty()){
            throw new NoContentException("Se requieren datos para poder mostrar la lista");
        }
        var inventarios = result
                .stream()
                .map(this::converToDto)
                .toList();

        return Pagination
                .<InventarioDto>builder()
                .empty(result.isEmpty())
                .first(result.isFirst())
                .last(result.isLast())
                .last(result.isLast())
                .number(result.getNumber())
                .numberOfElement(result.getNumberOfElements())
                .size(result.getSize())
                .totalElements(result.getTotalElements())
                .totalPages(result.getTotalPages())
                .content(inventarios)
                .build();
    }
    @Transactional
    @Override
    public InventarioDto findById(int id) {
       var inventario = inventarioRepo.findById(id)
               .orElseThrow(() -> new NoContentException("No existe el ID"));

        return converToDto(inventario);
    }
    @Transactional
    @Override
    public InventarioDto save(InventarioDto inventario) {
        if(inventario == null ){
            throw new NoContentException("No hay inventario a guardar");
        }

       var newInventario = converToEntity(inventario);

       return converToDto(inventarioRepo.save(newInventario));
    }

    @Override
    public InventarioDto update(int id,InventarioDto inventario) {
        var existInventario= inventarioRepo.findById(id)
                .orElseThrow(() -> new NoContentException("No existe el ID"));


        BeanUtils.copyProperties(inventario, existInventario);

        inventarioRepo.save(existInventario);
        return inventario;
    }
    private Inventario converToEntity(InventarioDto inventarioDto){
        return modelMapper.map(inventarioDto, Inventario.class);
    }
    private InventarioDto converToDto(Inventario inventario){
        return modelMapper.map(inventario, InventarioDto.class);
    }
}
