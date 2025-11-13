package com.TPE.msFactura.service;

import com.TPE.msFactura.model.DetalleFactura;
import com.TPE.msFactura.repository.DetalleFacturaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleFacturaService implements IDetalleFacturaService{

    @Autowired
    private DetalleFacturaRepository detalleFacturaRepository;


    @Transactional
    public List<DetalleFactura> findAll() {
        return detalleFacturaRepository.findAll();
    }

    @Transactional
    public DetalleFactura findById(Long id) {
        return detalleFacturaRepository.findById(id).orElse(null);
    }

    @Transactional
    public DetalleFactura create(DetalleFactura detalleFactura) {
        return detalleFacturaRepository.save(detalleFactura);
    }

    @Transactional
    public DetalleFactura update(DetalleFactura detalleFactura) {
        return detalleFacturaRepository.save(detalleFactura);
    }

    @Transactional
    public void delete(Long id) {
        detalleFacturaRepository.deleteById(id);
    }
}
