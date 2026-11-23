package com.bodegas.service;

import com.bodegas.dto.BodegaRequest;
import com.bodegas.dto.BodegaResponse;
import com.bodegas.entity.Bodega;
import com.bodegas.repository.BodegaRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BodegaService {

    private final BodegaRepository repository;
    private final ModelMapper modelMapper;

    public BodegaResponse crear(BodegaRequest request) {
        Bodega bodega = modelMapper.map(request, Bodega.class);
        Bodega guardada = repository.save(bodega);
        return modelMapper.map(guardada, BodegaResponse.class);
    }

    @Transactional(readOnly = true)
    public List<BodegaResponse> listar() {
        return repository.findAllConProductos().stream()
                .map(bodega -> modelMapper.map(bodega, BodegaResponse.class))
                .toList();
    }
}
