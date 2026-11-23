package com.bodegas.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BodegaResponse {

    private Long id;
    private String nombre;
    private String ubicacion;
    private List<ProductoResponse> productos = new ArrayList<>();
}
