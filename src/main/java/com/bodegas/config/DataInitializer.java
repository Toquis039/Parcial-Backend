package com.bodegas.config;

import com.bodegas.entity.Bodega;
import com.bodegas.entity.Producto;
import com.bodegas.repository.BodegaRepository;
import com.bodegas.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final BodegaRepository bodegaRepository;
    private final ProductoRepository productoRepository;

    @Override
    @Transactional
    public void run(String... args) {
        if (bodegaRepository.count() > 0) {
            return;
        }

        Bodega norte = bodegaRepository.save(new Bodega("Bodega Norte", "Bogota - Zona Norte"));
        Bodega sur = bodegaRepository.save(new Bodega("Bodega Sur", "Bogota - Zona Sur"));

        Producto portatil = new Producto("Computador portatil", "Portatil de 15 pulgadas", new BigDecimal("2500000"), 15);
        Producto monitor = new Producto("Monitor", "Monitor LED de 24 pulgadas", new BigDecimal("750000"), 20);
        Producto impresora = new Producto("Impresora", "Impresora multifuncional", new BigDecimal("1200000"), 8);

        norte.agregarProducto(portatil);
        norte.agregarProducto(monitor);
        sur.agregarProducto(impresora);

        productoRepository.save(portatil);
        productoRepository.save(monitor);
        productoRepository.save(impresora);
    }
}
