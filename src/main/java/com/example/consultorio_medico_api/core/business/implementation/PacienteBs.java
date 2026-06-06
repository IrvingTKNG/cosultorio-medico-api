package com.example.consultorio_medico_api.core.business.implementation;

import com.example.consultorio_medico_api.core.business.input.PacienteService;
import com.example.consultorio_medico_api.core.business.output.PacienteRepository;
import com.example.consultorio_medico_api.core.entity.Paciente;
import com.example.consultorio_medico_api.utils.error.ErrorBs;
import com.example.consultorio_medico_api.utils.error.ErrorEnum;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    public Either<ErrorBs, Boolean> save(Paciente paciente) {
        if (paciente == null) {
            return Either.left(ErrorEnum.NULL_OBJECT);
        }
        var pacienteExistente = pacienteRepository.findByNumExpediente(paciente.getNumExpediente());
        if (pacienteExistente != null) {
            return Either.left(ErrorEnum.USER_DUPLICATED);
        }
        pacienteRepository.save(paciente);
        return Either.right(Boolean.TRUE);
    }
}
