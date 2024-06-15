package com.example.projeto.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.springframework.beans.BeanUtils;

import com.example.projeto.dto.ConsultaDTO;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "CONSULTA")
public class ConsultaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String queixaPrincipal;

	@Column(nullable = false)
	private String medico;

	@Column(nullable = false)
	private String diagnostico;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "paciente", nullable = false)
	@JsonIgnore
	private PatientEntity paciente;


	public ConsultaEntity(ConsultaDTO consultaDTO) {
		this.queixaPrincipal = consultaDTO.getQueixaPrincipal();
		this.medico = consultaDTO.getMedico();
		this.diagnostico = consultaDTO.getDiagnostico();

	}

	public ConsultaEntity() {
	}

	public void updateFromDTO(ConsultaDTO consultaDTO) {
		this.queixaPrincipal = consultaDTO.getQueixaPrincipal();
		this.medico = consultaDTO.getMedico();
		this.diagnostico = consultaDTO.getDiagnostico();
	}




	// Getters and setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getQueixaPrincipal() {
		return queixaPrincipal;
	}

	public void setQueixaPrincipal(String queixaPrincipal) {
		this.queixaPrincipal = queixaPrincipal;
	}

	public String getMedico() {
		return medico;
	}

	public void setMedico(String medico) {
		this.medico = medico;
	}

	public String getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}

	public PatientEntity getPaciente() {
		return paciente;
	}

	public void setPaciente(PatientEntity paciente) {
		this.paciente = paciente;
	}
}


