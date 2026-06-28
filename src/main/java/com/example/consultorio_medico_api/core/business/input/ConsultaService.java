package com.example.consultorio_medico_api.core.business.input;

import com.example.consultorio_medico_api.core.entity.Consulta;
import com.example.consultorio_medico_api.utils.error.ErrorBs;
import io.vavr.control.Either;

import java.util.List;

public interface ConsultaService {
    /**
     * Lista todas las consultas
     * @return
     */
    List<Consulta> listAll();

    /***
     * Trae una consulta por su id
     * @param id
     * @return
     */
    Either<ErrorBs,Consulta> getById(Integer id);

    /***
     * Guarda una consulta
     * @param consulta
     * @return
     */
    Either<ErrorBs,Boolean> create(Consulta consulta, Integer idCita);

    /***
     * Borra una consulta por su id
     * @param id
     * @return
     */
    Either<ErrorBs,Boolean> delete(Integer id);

    /***
     * Actualiza una consulta
     * @param id
     * @param consulta
     * @return
     */
    Either<ErrorBs,Boolean> update(Integer id, Consulta consulta);
}
