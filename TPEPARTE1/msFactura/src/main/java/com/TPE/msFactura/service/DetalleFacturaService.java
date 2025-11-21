package com.TPE.msFactura.service;

import com.TPE.msFactura.dto.UsoMonopatinUsuarioDTO;
import com.TPE.msFactura.model.DetalleFactura;
import com.TPE.msFactura.model.Factura;
import com.TPE.msFactura.repository.DetalleFacturaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class DetalleFacturaService implements IDetalleFacturaService{

    @Autowired
    private DetalleFacturaRepository detalleFacturaRepository;

    @Autowired
    private IFacturaService facturaService;


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

    public List<UsoMonopatinUsuarioDTO> obtenerUsuariosQueMasUsan(LocalDate fechaInicio, LocalDate fechaFin) {

        // 1️⃣ Traer facturas dentro del período
        List<Factura> facturas = facturaService.findByFechaEmisionBetween(fechaInicio, fechaFin);

        if (facturas.isEmpty()) {
            return Collections.emptyList();
        }

        // 2️⃣ De esas facturas, traer los detalles
        List<Long> facturaIds = facturas.stream()
                .map(Factura::getId)
                .toList();

        List<DetalleFactura> detalles = detalleFacturaRepository.findByFacturaIdIn((facturaIds));

        // 3️⃣ Agrupar por usuarioId → sumar tiempo total
        Map<Long, Long> tiempoPorUsuario = new HashMap<>();

        for (DetalleFactura df : detalles) {
            Long usuarioId = df.getFactura().getUsuarioId();

            long tiempoReal = df.getTiempoUso() - df.getTiempoPausado();

            tiempoPorUsuario.merge(usuarioId, tiempoReal, Long::sum);
        }


        return tiempoPorUsuario.entrySet().stream()
                .map(e -> new UsoMonopatinUsuarioDTO(e.getKey(), e.getValue()))
                .sorted(Comparator.comparingLong(UsoMonopatinUsuarioDTO::getTiempoTotalUso).reversed())
                .toList();
        }

}
