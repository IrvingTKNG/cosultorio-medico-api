package com.example.consultorio_medico_api.external.jpa.repository;

import com.example.consultorio_medico_api.external.jpa.jpaEntity.PacienteJpa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PacienteJpaRepository extends JpaRepository<PacienteJpa, Integer> {
    /***
     *Busca un paciente por su numero de expediente
     * @param numExpediente
     * @return
     */
    Optional<PacienteJpa> findByNumExpediente(String numExpediente);


}
