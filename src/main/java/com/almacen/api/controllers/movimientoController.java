package com.almacen.api.controllers;

import com.almacen.api.payloads.Dto.MovimientoDto;
import com.almacen.api.payloads.Dto.Pagination;
import com.almacen.api.service.movimientoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movimiento")
@RequiredArgsConstructor
public class movimientoController {

    private final movimientoService movimientoService;

    @GetMapping
    public ResponseEntity<Pagination<MovimientoDto>> findAll(@RequestParam("page") int page, @RequestParam("size") int size){
        var response = movimientoService.findAll(page, size);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovimientoDto> findById(@PathVariable("id") int id){
        var response = movimientoService.findById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<MovimientoDto> save(@RequestBody @Valid MovimientoDto movimientoDto){
        var response = movimientoService.save(movimientoDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
    @PutMapping("/{id}")
    public ResponseEntity<MovimientoDto> updateMovimiento(@RequestBody @Valid MovimientoDto movimientoDto, @PathVariable("id") int id){
        var response = movimientoService.update(movimientoDto, id);
        return ResponseEntity.ok(response);
    }


}

