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
    public List<Paciente> listAll() {
        return pacienteRepository.findAll();
    }

    @Override
    public Either<ErrorBs, Paciente> getById(Integer id) {
        var optionalPaciente = pacienteRepository.findById(id);
        return optionalPaciente.<Either<ErrorBs, Paciente>>map(Either::right).orElseGet(() -> Either.left(ErrorEnum.NOT_FOUND));
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
    @Transactional
    public Either<ErrorBs, Boolean> update(Integer idPaciente, Paciente paciente) {
        var optionalPaciente = pacienteRepository.findById(idPaciente);
        if (optionalPaciente.isEmpty()) {
            return Either.left(ErrorEnum.NOT_FOUND);
        }
        var getPaciente = optionalPaciente.get();
        getPaciente.setNombre(paciente.getNombre());
        getPaciente.setApellidoPaterno(paciente.getApellidoPaterno());
        getPaciente.setApellidoMaterno(paciente.getApellidoMaterno());
        getPaciente.setNumTelefono(paciente.getNumTelefono());
        getPaciente.setNumTelefonoAlterno(paciente.getNumTelefonoAlterno());
        getPaciente.setCorreo(paciente.getCorreo());
        getPaciente.setNumExpediente(paciente.getNumExpediente());
        getPaciente.setFhNacimiento(paciente.getFhNacimiento());
        pacienteRepository.save(getPaciente);
        return Either.right(Boolean.TRUE);
    }

    @Override
    @Transactional
    public Either<ErrorBs, Boolean> delete(Integer id) {
        var pacienteExists = pacienteRepository.existsById(id);
        if (pacienteExists.equals(false)) {
            return Either.left(ErrorEnum.NOT_FOUND);
        }
        pacienteRepository.deleteById(id);
        return Either.right(Boolean.TRUE);
    }
}
