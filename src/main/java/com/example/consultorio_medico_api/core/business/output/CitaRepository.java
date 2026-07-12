package com.example.consultorio_medico_api.core.business.output;

import com.example.consultorio_medico_api.core.entity.Cita;
import com.example.consultorio_medico_api.utils.paginador.Paginador;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface CitaRepository {

    Boolean existsByIdDoctorAndFecha(Integer idDoctor, LocalDate fecha, LocalTime tmInicio, LocalTime tmFin);

    /***
     * Trae una cita por su id
     * @param id
     * @return
     */
    Optional<Cita> findById(Integer id);

    /***
     * Lista todas las citas
     * @return
     */
    List<Cita> findAll();

    List<Cita> findByFecha(LocalDate fhInicio, LocalDate fhFin, Paginador paginador);

    /***
     * Verifica si existe una cita por su id
     * @param id
     * @return
     */
    Boolean existsById(Integer id);

    /***
     * Guarda una cita
     * @param cita
     */
    void save(Cita cita);

    /***
     * Borra una cita por su id
     * @param id
     */
    void deleteById(Integer id);
}
