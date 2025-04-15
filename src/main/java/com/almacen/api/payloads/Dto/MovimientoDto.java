package com.almacen.api.payloads.Dto;

import com.almacen.api.utils.ENUMS.Movimientos;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovimientoDto implements Serializable {

    private int id;

    @DecimalMin(value =  "0", inclusive = false, message = "La cantidad debe ser mayor a 0")
    private int cantidad;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "El tipo es obligatorio")
    private Movimientos movimientos;

    private String comentario;

    private List<ProductoDto> producto;
}
