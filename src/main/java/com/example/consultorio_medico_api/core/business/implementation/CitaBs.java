package com.example.consultorio_medico_api.core.business.implementation;

import com.example.consultorio_medico_api.core.business.input.CitaService;
import com.example.consultorio_medico_api.core.business.output.CitaRepository;
import com.example.consultorio_medico_api.core.business.statenmachine.CitaSM;
import com.example.consultorio_medico_api.core.entity.Cita;
import com.example.consultorio_medico_api.utils.error.ErrorBs;
import com.example.consultorio_medico_api.utils.error.ErrorEnum;
import com.example.consultorio_medico_api.utils.paginador.Paginador;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static com.example.consultorio_medico_api.core.business.statenmachine.CitaSM.ST_AGENDADA;
import static com.example.consultorio_medico_api.core.business.statenmachine.CitaSM.ST_EDITADA;
import static com.example.consultorio_medico_api.utils.DateUtils.DEFAULT_ZONE_ID;

@Service
@RequiredArgsConstructor
public class CitaBs implements CitaService {
    private final CitaRepository citaRepository;
    private final CitaSM citaSM;

    //TODO:
    // Agregar filtro por doctor

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
    public List<Cita> listAll() {
        return citaRepository.findAll();
    }

    @Override
    public List<Cita> listByFecha(LocalDate fhInicio, LocalDate fhFin, Paginador paginador) {
        return citaRepository.findByFecha(fhInicio, fhFin, paginador);
    }


    @Override
    @Transactional
    public Either<ErrorBs, Boolean> create(Cita cita) {
        if (cita.getFecha().isBefore(LocalDate.now(DEFAULT_ZONE_ID))) {
            return Either.left(ErrorEnum.INVALID_DATE);
        }
        var existsCitaProgramada = citaRepository.existsByIdDoctorAndFecha(cita.getIdDoctor(),
                cita.getFecha(), cita.getHoraInicio(), cita.getHoraFin());
        if (existsCitaProgramada.equals(Boolean.TRUE)) {
            return Either.left(ErrorEnum.CITA_DUPLICATED);
        }
        //Mejora: Agregar indicaciones para la cita
        cita.setIdEstado(ST_AGENDADA);
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
        getCita.setIdDoctor(cita.getIdDoctor());
        getCita.setHoraFin(cita.getHoraFin());
        getCita.setIdEstado(ST_EDITADA);
        citaRepository.save(getCita);
        return Either.right(Boolean.TRUE);
    }
}
