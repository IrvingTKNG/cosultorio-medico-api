package com.example.consultorio_medico_api.core.business.output;

import com.example.consultorio_medico_api.core.entity.Cita;

import java.util.List;
import java.util.Optional;

public interface CitaRepository {
    List<Cita> findAll();

    Optional<Cita> findById(Integer id);

    Boolean existsById(Integer id);

    void save(Cita cita);

    void deleteById(Integer id);
}
