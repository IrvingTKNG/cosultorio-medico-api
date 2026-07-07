package com.example.consultorio_medico_api.external.jpa.dao;

import com.example.consultorio_medico_api.core.business.output.RecetaRepository;
import com.example.consultorio_medico_api.core.entity.Receta;
import com.example.consultorio_medico_api.external.jpa.entity.RecetaJpa;
import com.example.consultorio_medico_api.external.jpa.repository.RecetaJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RecetaDao implements RecetaRepository {
    private final RecetaJpaRepository recetaJpaRepository;

    @Override
    public List<Receta> findAll() {
        return recetaJpaRepository.findAll().stream().map(RecetaJpa::toEntity).toList();
    }

    @Override
    public Boolean existsById(Integer id) {
        return recetaJpaRepository.existsById(id);
    }

    @Override
    public Optional<Receta> findById(Integer id) {
        var recetaJpa = recetaJpaRepository.findById(id);
        return recetaJpa.map(RecetaJpa::toEntity);
    }

    @Override
    public void save(Receta receta) {
        recetaJpaRepository.saveAndFlush(RecetaJpa.fromEntity(receta));
    }

    @Override
    public void deleteById(Integer id) {
        recetaJpaRepository.deleteById(id);
    }
}
