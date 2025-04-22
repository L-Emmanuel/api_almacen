package com.almacen.api.controllers;

import com.almacen.api.payloads.Dto.Pagination;
import com.almacen.api.payloads.Dto.ProveedorDto;
import com.almacen.api.service.proveedorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/proveedor")
@RequiredArgsConstructor
public class proveedorController {

    private final proveedorService proveedorService;

    @GetMapping
    public ResponseEntity<Pagination<ProveedorDto>> findAll(@RequestParam("page") int page, @RequestParam("size") int size){
        var response = proveedorService.findAll(page, size);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProveedorDto> findById(@PathVariable("id") int id){
        var response = proveedorService.findById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ProveedorDto> addProveedor(@RequestBody @Valid ProveedorDto proveedorDto){
        var response = proveedorService.save(proveedorDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProveedorDto> updateProveedor(@RequestBody @Valid ProveedorDto proveedorDto, @PathVariable int id){
        var response = proveedorService.update(proveedorDto, id);
        return ResponseEntity.ok(response);
    }


}
