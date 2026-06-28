package com.example.consultorio_medico_api.core.business.input;

import com.example.consultorio_medico_api.core.entity.Cita;
import com.example.consultorio_medico_api.utils.error.ErrorBs;
import io.vavr.control.Either;

import java.util.List;

public interface CitaService {
    List<Cita> listAll();
    Either<ErrorBs,Cita> getById(Integer id);
    Either<ErrorBs,Boolean> create(Cita cita);
    Either<ErrorBs,Boolean> delete(Integer id);
    Either<ErrorBs,Boolean> update(Integer id, Cita cita);
}
