package com.example.consultorio_medico_api.external.rest.dto;

import com.example.consultorio_medico_api.core.entity.Receta;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

import static com.example.consultorio_medico_api.utils.DateUtils.LOCAL_DATE_FORMAT;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Receta", description = "Contiene información de una receta")
public class RecetaDto {
    @JsonProperty
    @Schema(description = "Identificador de la receta", accessMode = Schema.AccessMode.READ_ONLY)
    private Integer id;
    @JsonProperty
    @Schema(description = "Identificador de la consulta", accessMode = Schema.AccessMode.READ_ONLY)
    private Integer idConsulta;
    @JsonProperty
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = LOCAL_DATE_FORMAT)
    @Schema(description = "Fecha para la receta", format = "string", implementation = String.class, example = "01/01/2010", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDate fecha;
    @JsonProperty
    @Schema(description = "Indicaciones de la receta", requiredMode = Schema.RequiredMode.REQUIRED)
    private String indicaciones;
    @JsonProperty
    @Schema(description = "Detalles de la receta", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<RecetaDetallesDto> detalles;

    public static RecetaDto fromEntity(Receta entity) {
        return RecetaDto.builder()
                .id(entity.getId())
                .idConsulta(entity.getIdConsulta())
                .fecha(entity.getFecha())
                .indicaciones(entity.getIndicaciones())
                .detalles(entity.getDetalles().stream()
                        .map(RecetaDetallesDto::fromEntity)
                        .toList()
                )
                .build();
    }

    public Receta toEntity() {
        return Receta.builder()
                .id(id)
                .idConsulta(idConsulta)
                .fecha(fecha)
                .indicaciones(indicaciones)
                .detalles(detalles.stream()
                        .map(RecetaDetallesDto::toEntity)
                        .toList()
                )
                .build();
    }
}
