package com.bodegas.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BodegaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void get_bodegas_retorna_200_con_productos_de_prueba() throws Exception {
        mockMvc.perform(get("/bodegas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Bodega Norte"))
                .andExpect(jsonPath("$[0].productos.length()").value(2))
                .andExpect(jsonPath("$[1].nombre").value("Bodega Sur"))
                .andExpect(jsonPath("$[1].productos[0].nombre").value("Impresora"));
    }

    @Test
    void post_bodegas_valida_retorna_201_con_id() throws Exception {
        mockMvc.perform(post("/bodegas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"Bodega Centro\",\"ubicacion\":\"Medellin\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.nombre").value("Bodega Centro"));
    }

    @Test
    void post_bodegas_con_nombre_vacio_retorna_400() throws Exception {
        mockMvc.perform(post("/bodegas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"\",\"ubicacion\":\"Medellin\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors.nombre").exists());
    }
}
