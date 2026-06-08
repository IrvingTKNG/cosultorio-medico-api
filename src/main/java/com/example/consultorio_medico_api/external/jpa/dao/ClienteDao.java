package com.example.consultorio_medico_api.external.jpa.dao;

import com.example.consultorio_medico_api.core.business.output.PacienteRepository;
import com.example.consultorio_medico_api.core.entity.Paciente;
import com.example.consultorio_medico_api.external.jpa.jpaEntity.PacienteJpa;
import com.example.consultorio_medico_api.external.jpa.repository.PacienteJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ClienteDao implements PacienteRepository {

    private final PacienteJpaRepository pacienteJpaRepository;

    @Override
    public Optional<Paciente> findById(Integer id) {
        var pacienteJpa = pacienteJpaRepository.findById(id);
        return pacienteJpa.map(PacienteJpa::toEntity);
    }

    @Override
    public List<Paciente> findAll() {
        List<PacienteJpa> pacientesJpa = pacienteJpaRepository.findAll();
        return pacientesJpa.stream().map(PacienteJpa::toEntity).toList();
    }

    @Override
    public void save(Paciente paciente) {
        pacienteJpaRepository.save(PacienteJpa.fromEntity(paciente));
    }

    @Override
    public Optional<Paciente> findByNumExpediente(String numExpediente) {
        var pacienteJpa = pacienteJpaRepository.findByNumExpediente(numExpediente);
        return pacienteJpa.map(PacienteJpa::toEntity);
    }

    @Override
    public Boolean deleteById(Integer id) {
        return pacienteJpaRepository.deletePacienteJpaById(id);
    }
}
