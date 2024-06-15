package com.example.projeto.service;

import com.example.projeto.dto.ConsultaDTO;
import com.example.projeto.entity.ConsultaEntity;
import com.example.projeto.entity.PatientEntity;
import com.example.projeto.repository.ConsultaRepository;
import com.example.projeto.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final PatientRepository patientRepository;

    @Autowired
    public ConsultaService(ConsultaRepository consultaRepository, PatientRepository patientRepository ) {
        this.consultaRepository = consultaRepository;
        this.patientRepository = patientRepository;
    }

    public List<ConsultaEntity> getAllConsultas() {
        return consultaRepository.findAll();
    }

    public Optional<ConsultaEntity> getConsultaById(Long id) {
        return consultaRepository.findById(id);
    }

    public ConsultaEntity createConsulta(ConsultaDTO consultaDTO, Long pacienteId) {
        PatientEntity paciente = patientRepository.findById(pacienteId)
                .orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado com o Id:" + pacienteId));

        ConsultaEntity consulta = new ConsultaEntity();
        consulta.updateFromDTO(consultaDTO);
        consulta.setPaciente(paciente);

        return consultaRepository.save(consulta);
    }

    public Optional<ConsultaEntity> updateConsulta(Long id, ConsultaDTO consultaDetails, Long pacienteId) {
        return consultaRepository.findById(id)
                .map(consulta -> {

                    PatientEntity paciente = patientRepository.findById(pacienteId)
                                    .orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado com o Id: " + pacienteId));

                    BeanUtils.copyProperties(consultaDetails, consulta);
                    consulta.setPaciente(paciente);
                    return consultaRepository.save(consulta);
                });
    }

    public boolean deleteConsulta(Long id) {
        return consultaRepository.findById(id)
                .map(consulta -> {
                    consultaRepository.delete(consulta);
                    return true;
                }).orElse(false);
    }
}

