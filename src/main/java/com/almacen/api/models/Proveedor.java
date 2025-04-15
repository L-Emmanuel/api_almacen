package com.almacen.api.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
public class Proveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_proveedor;
    @NotBlank
    @Size(min = 2, max = 100, message = "El nombre debe tener min 2 caracteres y maximo 100")
    private String nombre;

    @NotBlank
    @Size(min = 2, max = 100, message = "La marca debe tener min 2 y maximo 100")
    private String empresa;

    @Pattern(regexp = "\\d{10,15}", message = "El teléfono debe contener solo números y tener entre 10 y 15 dígitos")
    private String telefono;
}
