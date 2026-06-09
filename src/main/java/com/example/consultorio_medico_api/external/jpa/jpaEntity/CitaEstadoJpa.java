package com.example.consultorio_medico_api.external.jpa.jpaEntity;

import com.example.consultorio_medico_api.core.entity.CitaEstado;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "cita_estado")
public class CitaEstadoJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado")
    private Integer id;
    @Column(name = "tx_nombre")
    private String nombre;

    public static CitaEstadoJpa fromEntity(CitaEstado entity) {
        return CitaEstadoJpa.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .build();
    }

    public CitaEstado toEntity() {
        return CitaEstado.builder()
                .id(id)
                .nombre(nombre)
                .build();
    }
}
