package com.example.projeto.service;

import com.example.projeto.dto.PatientDTO;
import com.example.projeto.entity.PatientEntity;
import com.example.projeto.repository.PatientRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public List<PatientEntity> getAllPatients() {
        return patientRepository.findAll();
    }

    public Optional<PatientEntity> getPatientById(Long id) {
        return patientRepository.findById(id);
    }

    public PatientEntity createPatient(PatientDTO patientDTO) {
        PatientEntity patient = new PatientEntity(patientDTO);
        return patientRepository.save(patient);
    }

    public Optional<PatientEntity> updatePatient(Long id, PatientDTO patientDetails) {
        return patientRepository.findById(id)
                .map(patient -> {
                    BeanUtils.copyProperties(patientDetails, patient);
                    return patientRepository.save(patient);
                });
    }

    public boolean deletePatient(Long id) {
        return patientRepository.findById(id)
                .map(patient -> {
                    patientRepository.delete(patient);
                    return true;
                }).orElse(false);
    }
}

