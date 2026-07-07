package com.example.consultorio_medico_api.core.business.input;

import com.example.consultorio_medico_api.core.entity.Receta;
import com.example.consultorio_medico_api.utils.error.ErrorEnum;
import io.vavr.control.Either;

import java.util.List;

public interface RecetaService {
    List<Receta> listAll();
    Either<ErrorEnum, Receta> getById(Integer id);
    Either<ErrorEnum, Boolean> create(Receta receta, Integer idConsulta);
    Either<ErrorEnum, Boolean> delete(Integer id);
    Either<ErrorEnum, Boolean> update(Integer id, Receta receta);
}
