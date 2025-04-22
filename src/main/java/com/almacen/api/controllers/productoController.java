package com.almacen.api.controllers;


import com.almacen.api.payloads.Dto.Pagination;
import com.almacen.api.payloads.Dto.ProductoDto;
import com.almacen.api.service.productoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/producto")
@RequiredArgsConstructor
public class productoController {

    private final productoService productoService;

    @GetMapping
    public ResponseEntity<Pagination<ProductoDto>> findAll(@RequestParam("page") int page, @RequestParam("size") int size){
        var response = productoService.findAll(page, size);
        return  ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDto> findById(@PathVariable("id") int id){
        var response = productoService.findById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ProductoDto> addProducto(@RequestBody @Valid ProductoDto productoDto){
        var response = productoService.save(productoDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoDto> updateProducto(@RequestBody @Valid  ProductoDto productoDto, @PathVariable("id") int id){
        var response = productoService.update(productoDto, id);
        return ResponseEntity.ok(response);
    }





}
