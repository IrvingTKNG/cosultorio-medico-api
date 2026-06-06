package com.example.consultorio_medico_api.core.business.implementation;

import com.example.consultorio_medico_api.core.business.input.PacienteService;
import com.example.consultorio_medico_api.core.business.output.PacienteRepository;
import com.example.consultorio_medico_api.core.entity.Paciente;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteBs implements PacienteService {

    private final PacienteRepository pacienteRepository;

    public PacienteBs(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public List<Paciente> findAll() {
        return pacienteRepository.findAll();
    }
}
