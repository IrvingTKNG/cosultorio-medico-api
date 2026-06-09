package com.example.consultorio_medico_api.core.business.implementation;

import com.example.consultorio_medico_api.core.business.input.CitaService;
import com.example.consultorio_medico_api.core.business.output.CitaRepository;
import com.example.consultorio_medico_api.core.entity.Cita;
import com.example.consultorio_medico_api.utils.error.ErrorBs;
import com.example.consultorio_medico_api.utils.error.ErrorEnum;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CitaBs implements CitaService {
    private final CitaRepository citaRepository;

    @Override
    public List<Cita> listAll() {
        return citaRepository.findAll();
    }

    @Override
    public Either<ErrorBs, Cita> getById(Integer id) {
        var findCita = citaRepository.findById(id);
        if (findCita.isEmpty()) {
            return Either.left(ErrorEnum.NOT_FOUND);
        }
        var cita = findCita.get();
        return Either.right(cita);
    }

    @Override
    @Transactional
    public Either<ErrorBs, Boolean> create(Cita cita) {
        //TODO: no puede haber dos citas con la misma fecha y hora para el mismo paciente y doctor
        //Mejora: Agregar indicaciones para la cita
        citaRepository.save(cita);
        return Either.right(Boolean.TRUE);
    }

    @Override
    @Transactional
    public Either<ErrorBs, Boolean> delete(Integer id) {
        var citaExists = citaRepository.existsById(id);
        if (citaExists.equals(false)) {
            return Either.left(ErrorEnum.NOT_FOUND);
        }
        citaRepository.deleteById(id);
        return Either.right(Boolean.TRUE);
    }

    @Override
    @Transactional
    public Either<ErrorBs, Boolean> update(Integer id, Cita cita) {
        var findCita = citaRepository.findById(id);
        if (findCita.isEmpty()) {
            return Either.left(ErrorEnum.NOT_FOUND);
        }
        var getCita = findCita.get();
        getCita.setFecha(cita.getFecha());
        getCita.setHoraInicio(cita.getHoraInicio());
        getCita.setHoraFin(cita.getHoraFin());
        citaRepository.save(getCita);
        return Either.right(Boolean.TRUE);
    }
}
