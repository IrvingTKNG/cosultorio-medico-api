package com.example.consultorio_medico_api.core.business.implementation;

import com.example.consultorio_medico_api.core.business.input.PacienteService;
import com.example.consultorio_medico_api.core.business.output.PacienteRepository;
import com.example.consultorio_medico_api.core.entity.Paciente;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponse;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PacienteBs implements PacienteService {

    private final PacienteRepository pacienteRepository;

    @Override
    public List<Paciente> findAll() {
        return pacienteRepository.findAll();
    }

    @Override
    @Transactional
    public Boolean save(Paciente paciente) {
        if (paciente == null) {
           return Boolean.FALSE;
        }
        pacienteRepository.save(paciente);
        return Boolean.TRUE;
    }
}
