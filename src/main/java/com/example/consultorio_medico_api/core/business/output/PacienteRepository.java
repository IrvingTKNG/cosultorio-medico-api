package com.example.consultorio_medico_api.core.business.output;

import com.example.consultorio_medico_api.core.entity.Paciente;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PacienteRepository {
    /***
     * Verifica si existe un paciente por su id
     * @param id
     * @return
     */
    Boolean existsById(Integer id);
    /***
     * Busca un paciente por su id
     * @param id
     * @return
     */
    Optional<Paciente> findById(Integer id);

    /**
     * Trae todos los pacientes
     * @return
     */
    List<Paciente> findAll();

    /***
     * Guarda un paciente
     * @param paciente
     */
    void save(Paciente paciente);

    /***
     * busca un paciente por su numero de expediente
     * @param numExpediente
     * @return
     */

    Optional<Paciente> findByNumExpediente(String numExpediente);

    /***
     * Borra un paciente por su id
     * @param id
     */
    void deleteById(Integer id);
}
