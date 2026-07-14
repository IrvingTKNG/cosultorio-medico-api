package com.example.consultorio_medico_api.external.rest.controller;

import com.example.consultorio_medico_api.core.business.input.ConsultaService;
import com.example.consultorio_medico_api.external.rest.dto.ConsultaDto;
import com.example.consultorio_medico_api.utils.error.ErrorMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/consultas")
@Tag(name = "Consultas", description = "Endpoints relacionados con las consultas")
public class ConsultaController {
    private final ConsultaService consultaService;

    @GetMapping("/")
    @ApiResponse(responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ConsultaDto.class))))
    @Operation(operationId = "listAllConsulta", summary = "Obtiene todos las consultas.CU-CON-01", description = "Obtiene todos las consultas")
    public ResponseEntity<List<ConsultaDto>> listAllConsulta() {
        var respuesta = consultaService.listAll().stream().map(ConsultaDto::fromEntity).toList();
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/{idConsulta}")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = ConsultaDto.class)))
    @Operation(operationId = "getConsulta", summary = "Obtiene una consulta por su id.CU-CON-02", description = "Obtiene una consulta por su id")
    public ResponseEntity<ConsultaDto> getConsulta(@PathVariable Integer idConsulta) {
        var respuesta = consultaService.getById(idConsulta).map(ConsultaDto::fromEntity);
        return respuesta.fold(ErrorMapper::mapToResponseEntity, ResponseEntity::ok);
    }

    @PostMapping("/")
    @ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = Boolean.class)))
    @Operation(operationId = "createConsulta", summary = "Crea una consulta.CU-CON-03", description = "Crea una consulta")
    public ResponseEntity<Boolean> createConsulta(@Valid @RequestBody ConsultaDto consultaDto, Integer idCita) {
        var respuesta = consultaService.create(consultaDto.toEntity(), idCita);
        return respuesta.fold(
                ErrorMapper::mapToResponseEntity,
                success -> ResponseEntity.status(HttpStatus.CREATED).body(success)
        );
    }

    @DeleteMapping("/{idConsulta}")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = Boolean.class)))
    @Operation(operationId = "deleteConsulta", summary = "Elimina una consulta.CU-CON-04", description = "Elimina una consulta")
    public ResponseEntity<Boolean> deleteConsulta(@PathVariable Integer idConsulta) {
        var respuesta = consultaService.delete(idConsulta);
        return respuesta.fold(ErrorMapper::mapToResponseEntity, ResponseEntity::ok);
    }

    @PutMapping("/{idConsulta}")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = Boolean.class)))
    @Operation(operationId = "updateConsulta", summary = "Actualiza una consulta.CU-CON-05", description = "Actualiza una consulta")
    public ResponseEntity<Boolean> updateConsulta(@PathVariable Integer idConsulta, @Valid @RequestBody ConsultaDto consultaDto) {
        var respuesta = consultaService.update(idConsulta, consultaDto.toEntity());
        return respuesta.fold(ErrorMapper::mapToResponseEntity, ResponseEntity::ok);
    }

}
