package com.example.consultorio_medico_api.external.jpa.dao;

import com.example.consultorio_medico_api.core.business.output.CitaRepository;
import com.example.consultorio_medico_api.core.entity.Cita;
import com.example.consultorio_medico_api.external.jpa.jpaEntity.CitaJpa;
import com.example.consultorio_medico_api.external.jpa.repository.CitaJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CitaDao implements CitaRepository {
    private final CitaJpaRepository citaJpaRepository;


    @Override
    public List<Cita> findAll() {
        return citaJpaRepository.findAll().stream().map(citaJpa -> {
            final var cita = citaJpa.toEntity();
            cita.setEstado(citaJpa.getCitaestadojpa().getNombre());
            return cita;
        }).toList();
    }

    @Override
    public Optional<Cita> findById(Integer id) {
        return citaJpaRepository.findById(id).map(
                citaJpa -> {
                    final var cita = citaJpa.toEntity();
                    cita.setEstado(citaJpa.getCitaestadojpa().getNombre());
                    return cita;
                }
        );
    }

    @Override
    public Boolean existsById(Integer id) {
        return citaJpaRepository.existsById(id);
    }

    @Override
    public void save(Cita cita) {
        citaJpaRepository.save(CitaJpa.fromEntity(cita));
    }

    @Override
    public void deleteById(Integer id) {
        citaJpaRepository.deleteById(id);
    }
}
