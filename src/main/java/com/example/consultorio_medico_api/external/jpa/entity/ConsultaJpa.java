package com.example.consultorio_medico_api.external.jpa.entity;

import com.example.consultorio_medico_api.core.entity.Consulta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "consulta")
public class ConsultaJpa {
    @Id
    @SequenceGenerator(name = "consulta_id_seq", sequenceName = "consulta_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "consulta_id_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id_consulta")
    private Integer id;
    @Column(name = "fk_id_doctor")
    private Integer idDoctor;
    @Column(name = "fk_id_cita")
    private Integer idCita;
    @Column(name = "fh_consulta")
    private LocalDate fecha;
    @Column(name = "tx_observaciones")
    private String observaciones;
    @Column(name = "tx_diagnostico")
    private String diagnostico;
    @Column(name = "tx_motivo")
    private String motivo;

    //Joins
    @JoinColumn(name = "fk_id_doctor", referencedColumnName = "id_doctor", insertable = false,updatable = false )
    @ManyToOne(fetch = FetchType.LAZY)
    private DoctorJpa doctorjpa;
    @JoinColumn(name = "fk_id_cita", referencedColumnName = "id_cita", insertable = false,updatable = false )
    @ManyToOne(fetch = FetchType.LAZY)
    private CitaJpa citajpa;

    public static ConsultaJpa fromEntity(Consulta entity) {
        return ConsultaJpa.builder()
                .id(entity.getId())
                .idDoctor(entity.getIdDoctor())
                .idCita(entity.getIdCita())
                .fecha(entity.getFecha())
                .observaciones(entity.getObservaciones())
                .diagnostico(entity.getDiagnostico())
                .motivo(entity.getMotivo())
                .build();
    }
    public Consulta toEntity(){
        return Consulta.builder()
                .id(id)
                .idDoctor(idDoctor)
                .idCita(idCita)
                .fecha(fecha)
                .observaciones(observaciones)
                .diagnostico(diagnostico)
                .motivo(motivo)
                .build();
    }
}
