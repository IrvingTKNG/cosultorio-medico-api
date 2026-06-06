package com.example.consultorio_medico_api.core.business.output;

import com.example.consultorio_medico_api.core.entity.Paciente;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PacienteRepository {
    List<Paciente> findAll();
    void save(Paciente paciente);
    Optional<Paciente> findByNumExpediente(String numExpediente);
}
