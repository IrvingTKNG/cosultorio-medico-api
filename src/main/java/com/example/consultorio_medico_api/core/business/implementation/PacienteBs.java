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

import java.time.OffsetDateTime;
import java.util.List;

import static com.example.consultorio_medico_api.utils.DateUtils.DEFAULT_ZONE_ID;

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
            return Either.left(ErrorEnum.NOT_FOUND);
        }
        var pacienteExistente = pacienteRepository.findByNumExpediente(paciente.getNumExpediente());
        if (pacienteExistente.isPresent()) {
            return Either.left(ErrorEnum.USER_DUPLICATED);
        }
        paciente.setFhRegistro(OffsetDateTime.now(DEFAULT_ZONE_ID));
        pacienteRepository.save(paciente);
        return Either.right(Boolean.TRUE);
    }

    @Override
    public Either<ErrorBs, Boolean> delete(Integer id) {
        var pacienteOptional = pacienteRepository.findById(id);
        if (pacienteOptional.isEmpty()){
            return Either.left(ErrorEnum.NOT_FOUND);
        }
        pacienteRepository.deleteById(id);
        return Either.right(Boolean.TRUE);
    }
}
