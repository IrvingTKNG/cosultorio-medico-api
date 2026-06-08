package com.example.consultorio_medico_api.core.business.input;

import com.example.consultorio_medico_api.core.entity.Paciente;
import com.example.consultorio_medico_api.utils.error.ErrorBs;
import io.vavr.control.Either;

import java.util.List;

public interface PacienteService {
    /***
     * Trae todos los pacientes
     * @return  List<Paciente>
     */
    List<Paciente> findAll();

    /***
     * Guarda un paciente
     * @param paciente
     * @return
     */
    Either<ErrorBs,Boolean> save(Paciente paciente);

    /***
     * Borra un paciente por su id
     * @param id
     * @return
     */
    Either<ErrorBs,Boolean> delete(Integer id);
}
