package com.example.consultorio_medico_api.core.business.input;

import com.example.consultorio_medico_api.core.entity.Paciente;
import io.vavr.control.Either;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponse;

import java.util.List;

public interface PacienteService {
    /***
     * Trae todos los pacientes
     * @return
     */
    List<Paciente> findAll();
}
