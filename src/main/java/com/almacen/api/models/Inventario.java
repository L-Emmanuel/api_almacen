package com.almacen.api.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
public class Inventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_inventario;

    @DecimalMin(value =  "0", inclusive = false, message = "La cantidad ingresada debe ser mayor a 0")
   private int cantidad_ingresada;

    private LocalDate fecha_ingreso;

    @DecimalMin(value =  "0", inclusive = false, message = "La cantidad actual debe ser mayor a 0")
    private int cantidad_actual;

    @ManyToOne
    @JoinColumn(name = "id_producto")
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "id_proveedor")
    private Proveedor proveedor;
}
