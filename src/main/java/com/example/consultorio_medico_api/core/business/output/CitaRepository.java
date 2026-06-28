package com.example.consultorio_medico_api.core.business.output;

import com.example.consultorio_medico_api.core.entity.Cita;

import java.util.List;
import java.util.Optional;

public interface CitaRepository {
    /***
     * Lista todas las citas
     * @return
     */
    List<Cita> findAll();

    /***
     * Trae una cita por su id
     * @param id
     * @return
     */
    Optional<Cita> findById(Integer id);

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
