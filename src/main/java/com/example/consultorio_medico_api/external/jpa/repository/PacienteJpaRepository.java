package com.example.consultorio_medico_api.external.jpa.repository;

import com.example.consultorio_medico_api.external.jpa.jpaEntity.PacienteJpa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PacienteJpaRepository extends JpaRepository<PacienteJpa, Integer> {

    List<PacienteJpa> findAll();

    Optional<PacienteJpa> findByNumExpediente(String numExpediente);
}
