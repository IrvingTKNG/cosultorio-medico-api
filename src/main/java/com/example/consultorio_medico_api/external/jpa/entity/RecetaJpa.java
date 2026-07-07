package com.example.consultorio_medico_api.external.jpa.entity;

import com.example.consultorio_medico_api.core.entity.Receta;
import com.example.consultorio_medico_api.core.entity.RecetaDetalles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "receta")
public class RecetaJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_receta")
    private Integer id;
    @Column(name = "fk_id_consulta")
    private Integer idConsulta;
    @Column(name = "fh_receta")
    private LocalDate fecha;
    @Column(name = "tx_indicaciones")
    private String indicaciones;
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "json_detalles", columnDefinition = "jsonb")
    private List<RecetaDetalles> detalles;

        public static RecetaJpa fromEntity(Receta entity) {
        return RecetaJpa.builder()
                .id(entity.getId())
                .idConsulta(entity.getIdConsulta())
                .fecha(entity.getFecha())
                .indicaciones(entity.getIndicaciones())
                .detalles(entity.getDetalles())
                .build();
    }

    public Receta toEntity() {
        return Receta.builder()
                .id(id)
                .idConsulta(idConsulta)
                .fecha(fecha)
                .indicaciones(indicaciones)
                .detalles(detalles)
                .build();
    }
}
