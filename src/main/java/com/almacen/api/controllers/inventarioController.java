package com.almacen.api.controllers;


import com.almacen.api.payloads.Dto.InventarioDto;
import com.almacen.api.payloads.Dto.Pagination;
import com.almacen.api.service.inventarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventario")
@RequiredArgsConstructor
public class inventarioController {

    private final inventarioService inventarioService;

    @GetMapping
    public ResponseEntity<Pagination<InventarioDto>> findAll(@RequestParam("page") int page, @RequestParam("size") int size){
        var response = inventarioService.findAll(page, size);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventarioDto> findById(@PathVariable("id") int id){
        var response = inventarioService.findById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<InventarioDto> save(@RequestBody @Valid InventarioDto inventarioDto){
        var response = inventarioService.save(inventarioDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventarioDto> updateInventario(@RequestBody @Valid InventarioDto inventarioDto, @PathVariable("id") int id){
        var response = inventarioService.update(id,inventarioDto);
        return ResponseEntity.ok(response);
    }

}
