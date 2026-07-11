package com.example.consultorio_medico_api.utils.paginador;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Paginador {
    private Integer numeroPagina;
    private Integer cantidadFilas;
}
