package com.example.consultorio_medico_api.external.rest.dto;

import com.example.consultorio_medico_api.core.entity.RecetaDetalles;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Receta", description = "Contiene información de los detalles de la receta")
public class RecetaDetallesDto {
    @JsonProperty
    @Schema(description = "Nombre del medicamento")
    private String medicamento;
    @JsonProperty
    @Schema(description = "Dosis o detalles para el medicamento")
    private String dosis;

    public static RecetaDetallesDto fromEntity(RecetaDetalles entity){
        return RecetaDetallesDto.builder()
                .medicamento(entity.getMedicamento())
                .dosis(entity.getDosis())
                .build();
    }
    public RecetaDetalles toEntity(){
        return RecetaDetalles.builder()
                .medicamento(medicamento)
                .dosis(dosis)
                .build();
    }
}
