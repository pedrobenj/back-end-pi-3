package com.example.projeto.controller;

import com.example.projeto.dto.ConsultaDTO;
import com.example.projeto.entity.ConsultaEntity;
import com.example.projeto.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @GetMapping
    public List<ConsultaEntity> getAllConsultas() {
        return consultaService.getAllConsultas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultaEntity> getConsultaById(@PathVariable Long id) {
        return consultaService.getConsultaById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{pacienteId}")
    public ConsultaEntity createConsulta(@RequestBody ConsultaDTO consultaDTO, @PathVariable Long pacienteId) {
        return consultaService.createConsulta(consultaDTO, pacienteId);
    }

    @PutMapping("/{id}/{pacienteId}")
    public ResponseEntity<ConsultaEntity> updateConsulta(@PathVariable Long id, @RequestBody ConsultaDTO consultaDetails, @PathVariable Long pacienteId) {
        return consultaService.updateConsulta(id, consultaDetails, pacienteId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteConsulta(@PathVariable Long id) {
        if (consultaService.deleteConsulta(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}


