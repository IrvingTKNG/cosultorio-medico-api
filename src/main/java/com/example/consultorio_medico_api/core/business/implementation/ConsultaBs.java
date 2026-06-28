package com.example.consultorio_medico_api.core.business.implementation;

import com.example.consultorio_medico_api.core.business.input.ConsultaService;
import com.example.consultorio_medico_api.core.business.output.ConsultaRepository;
import com.example.consultorio_medico_api.core.entity.Consulta;
import com.example.consultorio_medico_api.utils.error.ErrorBs;
import com.example.consultorio_medico_api.utils.error.ErrorEnum;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsultaBs implements ConsultaService {
    private final ConsultaRepository consultaRepository;

    @Override
    public List<Consulta> listAll() {
        return consultaRepository.findAll();
    }

    @Override
    public Either<ErrorBs, Consulta> getById(Integer id) {
        var consulta = consultaRepository.findById(id);
        if (consulta.isEmpty()) {
            return Either.left(ErrorEnum.NOT_FOUND);
        }
        var consultaGet = consulta.get();
        return Either.right(consultaGet);
    }

    @Override
    @Transactional
    public Either<ErrorBs, Boolean> create(Consulta consulta, Integer idCita) {
        consulta.setIdCita(idCita);
        consultaRepository.save(consulta);
        return Either.right(Boolean.TRUE);
    }

    @Override
    @Transactional
    public Either<ErrorBs, Boolean> delete(Integer id) {
        var consultaExists = consultaRepository.existsById(id);
        if (consultaExists.equals(false)) {
            return Either.left(ErrorEnum.NOT_FOUND);
        }
        consultaRepository.deleteById(id);
        return Either.right(Boolean.TRUE);
    }

    @Override
    @Transactional
    public Either<ErrorBs, Boolean> update(Integer id, Consulta consulta) {
        var consultaOptional = consultaRepository.findById(id);
        if (consultaOptional.isEmpty()) {
            return Either.left(ErrorEnum.NOT_FOUND);
        }
        var consultaGet = consultaOptional.get();
        consultaGet.setIdDoctor(consulta.getIdDoctor());
        consultaGet.setFecha(consulta.getFecha());
        consultaGet.setDiagnostico(consulta.getDiagnostico());
        consultaGet.setObservaciones(consulta.getObservaciones());
        consultaGet.setMotivo(consulta.getMotivo());
        consultaRepository.save(consultaGet);
        return Either.right(Boolean.TRUE);
    }
}
