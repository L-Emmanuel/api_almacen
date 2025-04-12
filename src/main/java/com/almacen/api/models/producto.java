package com.almacen.api.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProducto;

    @NotBlank
    @Size(min = 2, max = 100, message = "El nombre debe tener minimo dos caracteres y maximo 100")
    private String nombre;

    @NotBlank
    @Size(min = 2, max = 100, message = "La marca debe tener minimo dos caracteres y maximo 100")
    private String marca;

    @NotBlank
    @Size(min = 2, max = 100, message = "El tipo debe tener minimo dos caracteres y maximo 100")
    private String tipo;

    @DecimalMin(value =  "0.0", inclusive = false, message = "El precio debe ser mayor a 0")
    private double precio;

    private int cantidadDisponible;
}
