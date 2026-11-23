package com.bodegas.service;

import com.bodegas.config.ModelMapperConfig;
import com.bodegas.dto.BodegaRequest;
import com.bodegas.dto.BodegaResponse;
import com.bodegas.entity.Bodega;
import com.bodegas.entity.Producto;
import com.bodegas.repository.BodegaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BodegaServiceTest {

    @Mock
    private BodegaRepository repository;

    private BodegaService service;

    @BeforeEach
    void setUp() {
        service = new BodegaService(repository, new ModelMapperConfig().modelMapper());
    }

    @Test
    void crear_bodega_valida_retorna_respuesta_con_id() {
        BodegaRequest request = new BodegaRequest();
        request.setNombre("Bodega Centro");
        request.setUbicacion("Medellín");

        when(repository.save(any(Bodega.class))).thenAnswer(invocation -> {
            Bodega bodega = invocation.getArgument(0);
            ReflectionTestUtils.setField(bodega, "id", 1L);
            return bodega;
        });

        BodegaResponse response = service.crear(request);

        assertNotNull(response.getId());
        assertEquals("Bodega Centro", response.getNombre());
        assertEquals("Medellín", response.getUbicacion());
        verify(repository, times(1)).save(any(Bodega.class));
    }

    @Test
    void listar_retorna_bodegas_con_sus_productos() {
        Bodega norte = new Bodega("Bodega Norte", "Bogota");
        ReflectionTestUtils.setField(norte, "id", 1L);
        norte.agregarProducto(new Producto("Monitor", "LED 24", new BigDecimal("750000"), 20));

        when(repository.findAllConProductos()).thenReturn(List.of(norte));

        List<BodegaResponse> respuesta = service.listar();

        assertEquals(1, respuesta.size());
        assertEquals("Bodega Norte", respuesta.get(0).getNombre());
        assertEquals(1, respuesta.get(0).getProductos().size());
        assertEquals("Monitor", respuesta.get(0).getProductos().get(0).getNombre());
    }
}
