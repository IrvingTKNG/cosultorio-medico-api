package com.example.consultorio_medico_api.core.business.implementation;

import com.example.consultorio_medico_api.core.business.input.RecetaService;
import com.example.consultorio_medico_api.core.business.output.ConsultaRepository;
import com.example.consultorio_medico_api.core.business.output.RecetaRepository;
import com.example.consultorio_medico_api.core.entity.Receta;
import com.example.consultorio_medico_api.utils.error.ErrorEnum;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecetaBs implements RecetaService {

    private final RecetaRepository recetaRepository;
    private final ConsultaRepository consultaRepository;

    @Override
    public List<Receta> listAll() {
        return recetaRepository.findAll();
    }

    @Override
    public Either<ErrorEnum, Receta> getById(Integer id) {
        var recetaOptoptional = recetaRepository.findById(id);
        return recetaOptoptional.<Either<ErrorEnum, Receta>>map(Either::right).orElseGet(() -> Either.left(ErrorEnum.NOT_FOUND));
    }

    @Override
    @Transactional
    public Either<ErrorEnum, Boolean> create(Receta receta, Integer idConsulta) {
        var existsConsulta = consultaRepository.existsById(idConsulta);
        if (existsConsulta.equals(Boolean.FALSE)) {
            return Either.left(ErrorEnum.NOT_FOUND);
        }
        receta.setIdConsulta(idConsulta);
        recetaRepository.save(receta);
        return Either.right(Boolean.TRUE);
    }

    @Override
    @Transactional
    public Either<ErrorEnum, Boolean> delete(Integer id) {
        var exists = recetaRepository.existsById(id);
        if (exists.equals(false)) {
            return Either.left(ErrorEnum.NOT_FOUND);
        }
        recetaRepository.deleteById(id);
        return Either.right(Boolean.TRUE);
    }

    @Override
    @Transactional
    public Either<ErrorEnum, Boolean> update(Integer id, Receta receta) {
        var recetaOptional = recetaRepository.findById(id);
        if (recetaOptional.isEmpty()) {
            return Either.left(ErrorEnum.NOT_FOUND);
        }
        var recetaGet = recetaOptional.get();
        recetaGet.setFecha(receta.getFecha());
        recetaGet.setIndicaciones(receta.getIndicaciones());
        recetaGet.setDetalles(receta.getDetalles());
        recetaRepository.save(recetaGet);
        return Either.right(Boolean.TRUE);
    }
}
