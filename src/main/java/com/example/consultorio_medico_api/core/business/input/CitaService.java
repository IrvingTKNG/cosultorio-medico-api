package com.example.consultorio_medico_api.core.business.input;

import com.example.consultorio_medico_api.core.entity.Cita;
import com.example.consultorio_medico_api.utils.error.ErrorBs;
import com.example.consultorio_medico_api.utils.paginador.Paginador;
import io.vavr.control.Either;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface CitaService {
    /***
     * Trae una cita por su id
     * @param id
     * @return
     */
    Either<ErrorBs, Cita> getById(Integer id);

    /***
     * Lista todas las citas
     * @return
     */
    List<Cita> listAll();

    /***
     * Lista las citas por un rango de fecha
     * @param fhInicio
     * @param fhFin
     * @param paginador
     * @return
     */
    List<Cita> listByFecha(LocalDate fhInicio, LocalDate fhFin, Paginador paginador);

    /***
     * Lista las citas por un doctor
     * @param idDoctor
     * @return
     */
    List<Cita> listByIdDoctor(Integer idDoctor, Pageable pageable);

    /***
     * Guarda una cita
     * @param cita
     * @return
     */
    Either<ErrorBs, Boolean> create(Cita cita);

    /***
     * Borra una cita por su id
     * @param id
     * @return
     */
    Either<ErrorBs, Boolean> delete(Integer id);

    /***
     * Actualiza una cita
     * @param id
     * @param cita
     * @return
     */
    Either<ErrorBs, Boolean> update(Integer id, Cita cita);
}
