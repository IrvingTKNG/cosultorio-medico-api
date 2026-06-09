package com.example.consultorio_medico_api.external.rest.dto;

import com.example.consultorio_medico_api.core.entity.Cita;
import com.example.consultorio_medico_api.utils.DateUtils;
import com.example.consultorio_medico_api.utils.StringsConstants;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Cita", description = "Contiene información de la cita")
public class CitaDto {
    @JsonProperty
    @Schema(description = "Identificador del doctor", accessMode = Schema.AccessMode.READ_ONLY)
    private Integer id;
    @NotNull
    @JsonProperty
    @Schema(description = "Identificador del paciente", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer idPaciente;
    @NotNull
    @JsonProperty
    @Schema(description = "Identificador del doctor", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer idDoctor;
    @JsonProperty
    @Schema(description = "Identificador del estado de la cita")
    private Integer idEstado;
    @JsonProperty
    @Schema(description = "Estado de la cita")
    private String estado;
    @JsonProperty
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = StringsConstants.LOCAL_DATE_FORMAT)
    @Schema(description = "Fecha asignada para la cita del paciente", format = "string", implementation = String.class, example = "01/01/2010", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private LocalDate fechaCita;
    @JsonProperty
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.TIME_FORMAT)
    @Schema(description = "Hora de inicio de la cita", requiredMode = Schema.RequiredMode.REQUIRED, type = "string", example = "15:00:00")
    private LocalTime inicio;
    @JsonProperty
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.TIME_FORMAT)
    @Schema(description = "Hora de termino de la citar", requiredMode = Schema.RequiredMode.REQUIRED, type = "string", example = "15:00:00")
    private LocalTime fin;

    public static CitaDto fromEntity(Cita entity) {
        return CitaDto.builder()
                .id(entity.getId())
                .idPaciente(entity.getIdPaciente())
                .idDoctor(entity.getIdDoctor())
                .idEstado(entity.getIdEstado())
                .estado(entity.getEstado())
                .fechaCita(entity.getFecha())
                .inicio(entity.getHoraInicio())
                .fin(entity.getHoraFin())
                .build();
    }

    public Cita toEntity() {
        return Cita.builder()
                .id(id)
                .idPaciente(idPaciente)
                .idDoctor(idDoctor)
                .idEstado(idEstado)
                .estado(estado)
                .fecha(fechaCita)
                .horaInicio(inicio)
                .horaFin(fin)
                .build();
    }

}
