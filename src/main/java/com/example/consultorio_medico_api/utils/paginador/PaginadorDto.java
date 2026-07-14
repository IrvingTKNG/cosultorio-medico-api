package com.example.consultorio_medico_api.utils.paginador;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@Schema(name = "Paginador", description = "Dto para enviar la paginación de las listas de diversos objetos")
public class PaginadorDto {

    @Schema(description = "Número de página")
    private Integer numeroPagina;

    @Schema(description = "Cantidad de filas por página")
    private Integer cantidadFilas;


    public Paginador toEntity() {
        return Paginador.builder()
                .numeroPagina(numeroPagina)
                .cantidadFilas(cantidadFilas)
                .build();
    }
}
