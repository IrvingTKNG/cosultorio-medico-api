package com.example.consultorio_medico_api.external.jpa.dao;


import com.example.consultorio_medico_api.core.business.output.ConsultaRepository;
import com.example.consultorio_medico_api.core.entity.Consulta;
import com.example.consultorio_medico_api.external.jpa.entity.ConsultaJpa;
import com.example.consultorio_medico_api.external.jpa.repository.ConsultaJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ConsultaDao implements ConsultaRepository {
    private final ConsultaJpaRepository consultaJpaRepository;

    @Override
    public Boolean existsById(Integer id) {
        return consultaJpaRepository.existsById(id);
    }

    @Override
    public Optional<Consulta> findById(Integer id) {
        var consultaJpa = consultaJpaRepository.findById(id);
        return consultaJpa.map(ConsultaJpa::toEntity);
    }

    @Override
    public List<Consulta> findAll() {
        return consultaJpaRepository.findAll().stream().map(ConsultaJpa::toEntity).toList();
    }

    @Override
    public void save(Consulta consulta) {
        consultaJpaRepository.save(ConsultaJpa.fromEntity(consulta));
    }

    @Override
    public void deleteById(Integer id) {
        consultaJpaRepository.deleteById(id);
    }

}
