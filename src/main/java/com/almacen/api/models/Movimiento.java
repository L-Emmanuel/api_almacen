package com.almacen.api.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Movimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_movimiento;

    @DecimalMin(value =  "0", inclusive = false, message = "La cantidad debe ser mayor a 0")
    private int cantidad;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "El tipo es obligatorio")
    private com.almacen.api.utils.ENUMS.tipo_movimiento tipo_movimiento;

    private LocalDate fecha_movimiento;
    private String comentario;

    @ManyToOne
    @JoinColumn(name = "id_producto")
    private Producto producto;

}
