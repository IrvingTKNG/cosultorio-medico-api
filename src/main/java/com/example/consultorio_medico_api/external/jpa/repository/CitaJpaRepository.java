package com.example.consultorio_medico_api.external.jpa.repository;

import com.example.consultorio_medico_api.external.jpa.entity.CitaJpa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;

public interface CitaJpaRepository extends JpaRepository<CitaJpa, Integer> {

    @Query(value = """
            SELECT EXISTS (
                SELECT 1 FROM cita c
                WHERE c.fk_id_doctor = :idDoctor
                  AND c.fh_cita = :fecha
                   AND c.tm_inicio < :tmFin
                      AND c.tm_fin > :tmInicio
            )
            """, nativeQuery = true)
    boolean existeCita(@Param("idDoctor") Integer idDoctor,
                       @Param("fecha") LocalDate fecha,
                       @Param("tmInicio") LocalTime tmInicio,
                       @Param("tmFin") LocalTime tmFin);

    Page<CitaJpa> findByIdDoctor(Integer idDoctor, Pageable pageable);
}
