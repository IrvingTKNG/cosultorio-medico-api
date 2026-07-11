package com.example.consultorio_medico_api.external.rest.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

import static com.example.consultorio_medico_api.utils.DateUtils.LOCAL_DATE_FORMAT;

@Builder
@Getter
@Setter
@Schema(name = "FiltroFindByFechas", description = "Dto para los filtros de un rango de fechas.")
public class FiltroFindByFechasDto {

    @DateTimeFormat(pattern = LOCAL_DATE_FORMAT)
    @Schema(description = "Fecha de inicio del rango de búsqueda", example = "01/01/2026")
    private LocalDate fhInicio;

    @DateTimeFormat(pattern = LOCAL_DATE_FORMAT)
    @Schema(description = "Fecha de fin del rango de búsqueda", example = "31/01/2026")
    private LocalDate fhFin;

}
