package com.almacen.api.payloads.Dto;

import com.almacen.api.models.Proveedor;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventarioDto implements Serializable {

    private int id;

    @DecimalMin(value =  "0", inclusive = false, message = "La cantidad ingresada debe ser mayor a 0")
    private int cantidad_ingresada;

    private LocalDate fecha_ingreso;

    @DecimalMin(value =  "0", inclusive = false, message = "La cantidad actual debe ser mayor a 0")
    private int cantidad_actual;

    private List<String> producto;

    private ProveedorDto proveedor;
}
