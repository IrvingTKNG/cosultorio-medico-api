package com.example.consultorio_medico_api.external.jpa.repository;

import com.example.consultorio_medico_api.external.jpa.jpaEntity.DoctorJpa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DoctorJpaRepository extends JpaRepository<DoctorJpa, Integer> {
    /***
     * Busca un doctor por su cedula
     * @param cedula
     * @return
     */
    Optional<DoctorJpa> findByCedula(String cedula);
}
