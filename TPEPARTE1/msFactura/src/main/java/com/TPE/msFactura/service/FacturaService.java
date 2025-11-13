package com.TPE.msFactura.service;

import com.TPE.msFactura.model.Factura;
import com.TPE.msFactura.repository.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FacturaService implements IFacturaService{
    @Autowired
    private FacturaRepository facturaRepository;

    @Transactional
    public List<Factura> findAll() {
        return facturaRepository.findAll();
    }

    @Transactional
    public Factura findById(Long id) {
        return facturaRepository.findById(id).orElse(null);
    }

    @Transactional
    public Factura create(Factura factura) {
        return facturaRepository.save(factura);
    }

    @Transactional
    public Factura update(Factura factura) {
        return facturaRepository.save(factura);
    }

    @Transactional
    public void delete(Long id) {
        facturaRepository.deleteById(id);
    }

    /************************************************************/

    public double obtenerTotalFacturado(int anio, int mesInicio, int mesFin) {
        return facturaRepository.obtenerTotalFacturado(anio, mesInicio, mesFin);
    }
}
