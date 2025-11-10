package com.TPE.msParada.controller;

import com.TPE.msParada.model.Parada;
import com.TPE.msParada.service.IParadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parada")
public class ParadaController {

    @Autowired
    IParadaService iParadaService;

    //Registrar parada
    // Quitar parada

    @PostMapping("/add")
    public Parada addParada(Parada parada) {
        this.iParadaService.addParada(parada);
        return parada;
    }

    @PutMapping("/edit")
    public Parada editParada(@RequestBody Parada parada) {
        this.iParadaService.updateParada(parada);
        return parada;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteParada(@PathVariable Long id) {
        this.iParadaService.deleteParada(id);
        return "Parada deleted";
    }

    @GetMapping("/get")
    public List<Parada> getParadas() {
        return this.iParadaService.getParadas();
    }

    @GetMapping("/get/{id}")
    public Parada getParada(@PathVariable Long id) {
        return this.iParadaService.getParada(id);
    }

    @PostMapping("/add/monopatin/{idParada}/{idMonopatin}")
    public String addMonopatin(@PathVariable Long idParada,@PathVariable Long idMonopatin) {

        this.iParadaService.addMonopatin(idParada, idMonopatin);

        return "Monopatin added";
    }


}
