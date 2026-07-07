package com.example.consultorio_medico_api.core.business.output;

import com.example.consultorio_medico_api.core.entity.Receta;

import java.util.List;
import java.util.Optional;

public interface RecetaRepository {
    /***
     * Lista todas las recetas
     * @return
     */
    List<Receta> findAll();

    /**
     * Busca una receta exista por su id
     *
     * @param id
     * @return
     */
    Boolean existsById(Integer id);

    /***
     * Busca una receta por su id
     * @param id
     * @return
     */
    Optional<Receta> findById(Integer id);

    /***
     * Guarda una receta
     * @param receta
     */
    void save(Receta receta);

    /***
     * Borra una receta por su id
     * @param id
     */
    void deleteById(Integer id);
}
