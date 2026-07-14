package com.example.consultorio_medico_api.external.rest.controller;

import com.example.consultorio_medico_api.core.business.input.CitaService;
import com.example.consultorio_medico_api.external.rest.dto.CitaDto;
import com.example.consultorio_medico_api.external.rest.dto.FiltroFindByFechasDto;
import com.example.consultorio_medico_api.utils.error.ErrorMapper;
import com.example.consultorio_medico_api.utils.paginador.PaginadorDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/citas")
@Tag(name = "Citas", description = "Endpoints relacionados con las citas")
public class CitaController {
    private final CitaService citaService;

    @GetMapping("/")
    @ApiResponse(responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = CitaDto.class))))
    @Operation(operationId = "listAllCitas", summary = "Obtiene todos las citas.CU-CT-01", description = "Obtiene todas las citas registrados")
    public ResponseEntity<List<CitaDto>> listAllCitas() {
        var respuesta = citaService.listAll().stream().map(CitaDto::fromEntity).toList();
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/{idCita}")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = CitaDto.class)))
    @Operation(operationId = "getCita", summary = "Obtiene una cita por su id.CU-CT-02", description = "Obtiene una cita por su id")
    public ResponseEntity<CitaDto> getCita(@PathVariable Integer idCita) {
        var respuesta = citaService.getById(idCita).map(CitaDto::fromEntity);
        return respuesta.fold(ErrorMapper::mapToResponseEntity, ResponseEntity::ok);
    }

    @GetMapping("/by-fecha")
    @ApiResponse(responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = CitaDto.class))))
    @Operation(operationId = "listCitasByFecha", summary = "Obtiene todas las citas segun un rango de fechas.CU-CT-06", description = "Obtiene todas las citas segun un rango de fechas.")
    public ResponseEntity<List<CitaDto>> listCitasByFecha(
            @Valid @ModelAttribute FiltroFindByFechasDto filtro,
            @Valid @ModelAttribute PaginadorDto paginador) {
        var respuesta = citaService.listByFecha(filtro.getFhInicio(), filtro.getFhFin(), paginador.toEntity()).stream().map(CitaDto::fromEntity).toList();
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("listByDoctor/{idDoctor}")
    @ApiResponse(responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = CitaDto.class))))
    @Operation(operationId = "listCitaByIdDoctor", summary = "Obtiene todas las citas de un doctor.CU-CT-07", description = "Obtiene todas las citas de un doctor.")
    public ResponseEntity<List<CitaDto>> listCitaByIdDoctor(@PathVariable Integer idDoctor,
                                                           @ParameterObject @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        var respuesta = citaService.listByIdDoctor(idDoctor, pageable).stream().map(CitaDto::fromEntity).toList();
        return ResponseEntity.ok(respuesta);
    }

    @PostMapping("/")
    @ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = Boolean.class)))
    @Operation(operationId = "createCita", summary = "Crea una cita. CU-CT-03", description = "Crea una cita")
    public ResponseEntity<Boolean> createCita(@Valid @RequestBody CitaDto citaDto) {
        var respuesta = citaService.create(citaDto.toEntity());
        return respuesta.fold(ErrorMapper::mapToResponseEntity,
                success -> ResponseEntity.status(HttpStatus.CREATED).body(success));
    }

    @PutMapping("/{idCita}")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = Boolean.class)))
    @Operation(operationId = "updateCita", summary = "Actualiza una cita. CU-CT-04", description = "Actualiza una cita")
    public ResponseEntity<Boolean> updateCita(@PathVariable Integer idCita, @Valid @RequestBody CitaDto citaDto) {
        var respuesta = citaService.update(idCita, citaDto.toEntity());
        return respuesta.fold(ErrorMapper::mapToResponseEntity, ResponseEntity::ok);
    }

    @DeleteMapping("/{idCita}")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = Boolean.class)))
    @Operation(operationId = "deleteCita", summary = "Elimina una cita. CU-CT-05", description = "Elimina una cita")
    public ResponseEntity<Boolean> deleteCita(@PathVariable Integer idCita) {
        var respuesta = citaService.delete(idCita);
        return respuesta.fold(ErrorMapper::mapToResponseEntity, ResponseEntity::ok);
    }

}
