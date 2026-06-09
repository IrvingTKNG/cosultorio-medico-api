package com.example.consultorio_medico_api.external.rest.controller;

import com.example.consultorio_medico_api.core.business.input.PacienteService;
import com.example.consultorio_medico_api.external.rest.dto.PacienteDto;
import com.example.consultorio_medico_api.utils.error.ErrorMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping("/")
    @ApiResponse(responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = PacienteDto.class))))
    @Operation(operationId = "findAll", summary = "Obtiene todos los pacientes CU1", description = "Obtiene todos los pacientes registrados en el sistema")
    public ResponseEntity<List<PacienteDto>> findAllPacientes() {
        var pacientes = pacienteService.listAll().stream().map(PacienteDto::fromEntity)
                .toList();
        return ResponseEntity.ok(pacientes);
    }

    @GetMapping("/{idPaciente}")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = PacienteDto.class)))
    @Operation(operationId = "getPaciente", summary = "Obtiene a un paciente mediante su id CU2", description = "Obtiene a un paciente mediante su id registrados en el sistema")
    public ResponseEntity<PacienteDto> getPaciente(@PathVariable Integer idPaciente) {
        var paciente = pacienteService.getById(idPaciente).map(PacienteDto::fromEntity);
        return paciente.fold(ErrorMapper::mapToResponseEntity, ResponseEntity::ok);
    }

    @PostMapping("/")
    @ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = Boolean.class)))
    @Operation(operationId = "createPaciente", summary = "Crea un nuevo paciente CU3", description = "Crea un nuevo paciente")
    public ResponseEntity<Boolean> create(@Valid @RequestBody PacienteDto pacienteDto) {
        var result = pacienteService.save(pacienteDto.toEntity());
        return result.fold(ErrorMapper::mapToResponseEntity,
                success -> ResponseEntity.status(HttpStatus.CREATED).body(success));
    }

    @PostMapping("/{idPaciente}")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = Boolean.class)))
    @Operation(operationId = "updatePaciente", summary = "Actualiza un paciente CU4", description = "Actualiza un paciente")
    public ResponseEntity<Boolean> updatePaciente(@PathVariable Integer idPaciente, @Valid @RequestBody PacienteDto pacienteDto) {
        var result = pacienteService.update(idPaciente, pacienteDto.toEntity());
        return result.fold(ErrorMapper::mapToResponseEntity, ResponseEntity::ok);
    }

    @DeleteMapping("/{idPaciente}")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = Boolean.class)))
    @Operation(operationId = "deletePaciente", summary = "Elimina un paciente CU5", description = "Elimina un paciente")
    public ResponseEntity<Boolean> deletePaciente(@PathVariable Integer idPaciente) {
        var result = pacienteService.delete(idPaciente);
        return result.fold(ErrorMapper::mapToResponseEntity, ResponseEntity::ok);
    }
}
