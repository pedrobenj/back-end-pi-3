package com.example.projeto.controller;

import com.example.projeto.dto.PatientDTO;
import com.example.projeto.entity.PatientEntity;
import com.example.projeto.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/pacientes")
public class PatientController {

	@Autowired
	private PatientService patientService;

	@GetMapping
	public List<PatientEntity> getAllPatients() {
		return patientService.getAllPatients();
	}

	@GetMapping("/{id}")
	public ResponseEntity<PatientEntity> getPatientById(@PathVariable Long id) {
		return patientService.getPatientById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public PatientEntity createPatient(@RequestBody PatientDTO patientDTO) {
		return patientService.createPatient(patientDTO);
	}

	@PutMapping("/{id}")
	public ResponseEntity<PatientEntity> updatePatient(@PathVariable Long id, @RequestBody PatientDTO patientDetails) {
		return patientService.updatePatient(id, patientDetails)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePatient(@PathVariable Long id) {
		if (patientService.deletePatient(id)) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/nomes")
	public List<PatientDTO> getALLPatientNames(){
		List<PatientEntity> patients = patientService.getAllPatients();
		return patients.stream()
				.map(patient -> new PatientDTO(patient.getId(), patient.getName()))
				.collect(Collectors.toList());
	}
}

