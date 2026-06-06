package com.example.consultorio_medico_api.external.jpa.dao;

import com.example.consultorio_medico_api.core.business.output.PacienteRepository;
import com.example.consultorio_medico_api.core.entity.Paciente;
import com.example.consultorio_medico_api.external.jpa.jpaEntity.PacienteJpa;
import com.example.consultorio_medico_api.external.jpa.repository.PacienteJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClienteDao implements PacienteRepository {

    private final PacienteJpaRepository pacienteJpaRepository;
    public ClienteDao(PacienteJpaRepository pacienteJpaRepository) {
        this.pacienteJpaRepository = pacienteJpaRepository;
    }


    @Override
    public List<Paciente> findAll() {
        List<PacienteJpa> pacientes = pacienteJpaRepository.findAll();
        return pacientes.stream().map(PacienteJpa::toEntity).toList();
    }

    @Override
    public void save(Paciente paciente) {
        pacienteJpaRepository.save(PacienteJpa.fromEntity(paciente));
    }
}
