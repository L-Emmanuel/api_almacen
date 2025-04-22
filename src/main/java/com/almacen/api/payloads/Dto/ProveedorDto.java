package com.almacen.api.payloads.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProveedorDto implements Serializable {

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
