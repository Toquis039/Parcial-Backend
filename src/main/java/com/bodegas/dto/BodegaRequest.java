package com.bodegas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BodegaRequest {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 120, message = "El nombre admite maximo 120 caracteres")
    private String nombre;

    @NotBlank(message = "La ubicacion es obligatoria")
    @Size(max = 200, message = "La ubicacion admite maximo 200 caracteres")
    private String ubicacion;
}
