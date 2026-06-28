package com.example.consultorio_medico_api.core.business.output;

import com.example.consultorio_medico_api.core.entity.Consulta;

import java.util.List;
import java.util.Optional;

public interface ConsultaRepository {
    Boolean existsById(Integer id);
    /**
     * Busca una consulta por su id
     *
     * @param id
     * @return
     */
    Optional<Consulta> findById(Integer id);

    /***
     * Lista todas las consultas
     * @return
     */
    List<Consulta> findAll();

    /***
     * Guarda una consulta
     * @param consulta
     */
    void save(Consulta consulta);

    /***
     * Borra una consulta por su id
     * @param id
     */
    void deleteById(Integer id);
}
