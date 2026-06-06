package com.example.consultorio_medico_api.external.jpa.dao;

import com.example.consultorio_medico_api.core.business.output.PacienteRepository;
import com.example.consultorio_medico_api.core.entity.Paciente;
import com.example.consultorio_medico_api.external.jpa.jpaEntity.PacienteJpa;
import com.example.consultorio_medico_api.external.jpa.repository.PacienteJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ClienteDao implements PacienteRepository {

    private final PacienteJpaRepository pacienteJpaRepository;

    @Override
    public List<Paciente> findAll() {
        List<PacienteJpa> pacientes = pacienteJpaRepository.findAll();
        return pacientes.stream().map(PacienteJpa::toEntity).toList();
    }

    @Override
    public void save(Paciente paciente) {
        pacienteJpaRepository.save(PacienteJpa.fromEntity(paciente));
    }

    @Override
    public Paciente findByNumExpediente(String numExpediente) {
        return pacienteJpaRepository.findByNumExpediente(numExpediente).toEntity();
    }
}
