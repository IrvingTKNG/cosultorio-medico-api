package com.example.consultorio_medico_api.external.jpa.dao;

import com.example.consultorio_medico_api.core.business.output.CitaRepository;
import com.example.consultorio_medico_api.core.entity.Cita;
import com.example.consultorio_medico_api.external.jpa.entity.CitaJpa;
import com.example.consultorio_medico_api.external.jpa.repository.CitaJpaRepository;
import com.example.consultorio_medico_api.utils.paginador.Paginador;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Tuple;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.TypedParameterValue;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static com.example.consultorio_medico_api.utils.StringConstants.*;

@Repository
@RequiredArgsConstructor
public class CitaDao implements CitaRepository {
    private final CitaJpaRepository citaJpaRepository;

    @PersistenceContext
    private EntityManager entityManager;

    private static final String QUERY_FIND_BY_FECHAS = """
            select
              c.id_cita as idCita,
              c.fk_id_paciente as idPaciente,
              concat(p.tx_nombre , ' ', p.tx_apellido_paterno,' ', p.tx_apellido_materno ) as nombrePaciente,
              c.fk_id_doctor as idDoctor,
              concat(d.tx_nombre , ' ', d.tx_apellido_paterno,' ', d.tx_apellido_materno ) as nombreDoctor,
              c.fk_id_estado as idEstado,
              ce.tx_nombre as estado,
              c.fh_cita as fecha,
              c.tm_inicio as horaInicio,
              c.tm_fin as horaFin
            from cita c
            join cita_estado ce
              on c.fk_id_estado = ce.id_estado
            where c.fh_cita between :fhInicio and :fhFin
            order by c.fh_cita asc
            """;
    private static final String QUERY_PAGINADOR = """
             offset case
               when :numPagina is null or :cantidadFilas is null then 0
               else ((coalesce(cast(:numPagina as int), 0)) * coalesce(cast(:cantidadFilas as int), 0))
               end rows
               fetch next case
                 when :numPagina is null or :cantidadFilas is null then cast(null as int)
                 else cast(:cantidadFilas as int)
                 end rows only
            """;

    @Override
    public List<Cita> findAll() {
        return citaJpaRepository.findAll().stream().map(citaJpa -> {
            final var cita = citaJpa.toEntity();
            cita.setEstado(citaJpa.getCitaestadojpa().getNombre());
            return cita;
        }).toList();
    }

    @Override
    public List<Cita> findByFecha(LocalDate fhInicio, LocalDate fhFin, Paginador paginador) {
        final var QUERY_AND_PAGINADOR = QUERY_FIND_BY_FECHAS + QUERY_PAGINADOR;
        Stream<Tuple> stream = entityManager.createNativeQuery(QUERY_AND_PAGINADOR, Tuple.class)
                .setParameter(FECHA_INICIO, fhInicio)
                .setParameter(FECHA_FIN, fhFin)
                .setParameter(NUM_PAGINA, new TypedParameterValue<>(StandardBasicTypes.INTEGER, null))
                .setParameter(NUM_CANTIDAD_FILAS, new TypedParameterValue<>(StandardBasicTypes.INTEGER, null))
                .getResultStream();
        return stream.map(row -> Cita.builder()
                .id(((Number) row.get(ID_CITA)).intValue())
                .idPaciente(((Number) row.get(ID_PACIENTE)).intValue())
                .nombrePaciente(row.get(NOMBRE_PACIENTE, String.class))
                .idDoctor(((Number) row.get(ID_DOCTOR)).intValue())
                .nombreDoctor(row.get(NOMBRE_DOCTOR, String.class))
                .idEstado(((Number) row.get(ID_ESTADO)).intValue())
                .estado(row.get(ESTADO, String.class))
                .fecha(row.get(FECHA, LocalDate.class))
                .horaInicio(row.get(HORA_INICIO, LocalTime.class))
                .horaFin(row.get(HORA_FIN, LocalTime.class))
                .build()
        ).toList();
    }

    @Override
    public Boolean existsByIdDoctorAndFecha(Integer idDoctor, LocalDate fecha, LocalTime tmInicio, LocalTime tmFin) {
        return citaJpaRepository.existeCita(idDoctor.longValue(), fecha, tmInicio, tmFin);
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
