package com.example.consultorio_medico_api.external.jpa.jpaEntity;

import com.example.consultorio_medico_api.core.entity.Cita;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "cita")
public class CitaJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cita")
    private Integer id;
    @Column(name = "fk_id_doctor")
    private Integer idDoctor;
    @Column(name = "fk_id_paciente")
    private Integer idPaciente;
    @Column(name = "fk_id_estado")
    private Integer idEstado;
    @Column(name = "fh_cita")
    private LocalDate fechaCita;
    @Column(name = "tm_inicio")
    private LocalTime horaInicio;
    @Column(name = "tm_fin")
    private LocalTime horaFin;
    //JOINS

    @JoinColumn(name = "fk_id_doctor", referencedColumnName = "id_doctor", insertable = false,updatable = false )
    @ManyToOne(fetch = FetchType.LAZY)
    private DoctorJpa doctorjpa;

    @JoinColumn(name = "fk_id_paciente", referencedColumnName = "id_paciente", insertable = false,updatable = false )
    @ManyToOne(fetch = FetchType.LAZY)
    private PacienteJpa pacientejpa;

    @JoinColumn(name = "fk_id_estado", referencedColumnName = "id_estado", insertable = false,updatable = false )
    @ManyToOne(fetch = FetchType.LAZY)
    private CitaEstadoJpa citaestadojpa;

    public static CitaJpa fromEntity(Cita entity) {
        return CitaJpa.builder()
                .id(entity.getId())
                .idDoctor(entity.getIdDoctor())
                .idPaciente(entity.getIdPaciente())
                .idEstado(entity.getIdEstado())
                .fechaCita(entity.getFecha())
                .horaInicio(entity.getHoraInicio())
                .horaFin(entity.getHoraFin())
                .build();
    }

    public Cita toEntity(){
        return Cita.builder()
                .id(id)
                .idDoctor(idDoctor)
                .idPaciente(idPaciente)
                .idEstado(idEstado)
                .fecha(fechaCita)
                .horaInicio(horaInicio)
                .horaFin(horaFin)
                .build();
    }
}
