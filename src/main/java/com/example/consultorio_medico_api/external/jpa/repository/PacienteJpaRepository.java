package com.example.consultorio_medico_api.external.jpa.repository;

import com.example.consultorio_medico_api.external.jpa.jpaEntity.PacienteJpa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PacienteJpaRepository extends JpaRepository<PacienteJpa, Integer> {

    List<PacienteJpa> findAll();
}
