package com.example.consultorio_medico_api.core.business.input;

import com.example.consultorio_medico_api.core.entity.Cita;
import com.example.consultorio_medico_api.utils.error.ErrorBs;
import io.vavr.control.Either;

import java.util.List;

public interface CitaService {
    /***
     * Lista todas las citas
     * @return
     */
    List<Cita> listAll();

    /***
     * Trae una cita por su id
     * @param id
     * @return
     */
    Either<ErrorBs,Cita> getById(Integer id);

    /***
     * Guarda una cita
     * @param cita
     * @return
     */
    Either<ErrorBs,Boolean> create(Cita cita);

    /***
     * Borra una cita por su id
     * @param id
     * @return
     */
    Either<ErrorBs,Boolean> delete(Integer id);

    /***
     * Actualiza una cita
     * @param id
     * @param cita
     * @return
     */
    Either<ErrorBs,Boolean> update(Integer id, Cita cita);
}
