package com.example.consultorio_medico_api.external.rest.dto;

import com.example.consultorio_medico_api.core.entity.Consulta;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static com.example.consultorio_medico_api.utils.DateUtils.LOCAL_DATE_FORMAT;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Consulta", description = "Contiene información de la consulta")
public class ConsultaDto {
    @JsonProperty
    @Schema(description = "Identificador de la consulta", accessMode = Schema.AccessMode.READ_ONLY)
    private Integer id;
    @JsonProperty
    @Schema(description = "Identificador del doctor", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer idDoctor;
    @JsonProperty
    @Schema(description = "Identificador de la cita", accessMode = Schema.AccessMode.READ_ONLY)
    private Integer idCita;
    @JsonProperty
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = LOCAL_DATE_FORMAT)
    @Schema(description = "Fecha asignada para la cita del paciente", format = "string", implementation = String.class, example = "01/01/2010", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDate fecha;
    @JsonProperty
    @Schema(description = "Observaciones de la consulta")
    private String observaciones;
    @JsonProperty
    @Schema(description = "Diagnostico de la consulta")
    private String diagnostico;
    @JsonProperty
    @Schema(description = "Motivo de la consulta")
    private String motivo;

    public static ConsultaDto fromEntity(Consulta entity) {
        return ConsultaDto.builder()
                .id(entity.getId())
                .idDoctor(entity.getIdDoctor())
                .idCita(entity.getIdCita())
                .fecha(entity.getFecha())
                .observaciones(entity.getObservaciones())
                .diagnostico(entity.getDiagnostico())
                .motivo(entity.getMotivo())
                .build();
    }

    public Consulta toEntity() {
        return Consulta.builder()
                .idDoctor(idDoctor)
                .idCita(idCita)
                .fecha(fecha)
                .observaciones(observaciones)
                .diagnostico(diagnostico)
                .motivo(motivo)
                .build();
    }
}
