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
@RequestMapping("/api/pacientes")
public class ClienteController {
    private final PacienteService pacienteService;

    public ClienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping("/")
    @ApiResponse(responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = PacienteDto.class))))
    @Operation(operationId = "findAll", summary = "Obtiene todos los pacientes CU1", description = "Obtiene todos los pacientes registrados en el sistema")
    public ResponseEntity<List<PacienteDto>> findAll() {
        var pacientes = pacienteService.findAll().stream().map(PacienteDto::fromEntity)
                .toList();
        return ResponseEntity.ok(pacientes);
    }

    @PostMapping("/")
    @ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = Boolean.class)))
    @Operation(operationId = "createPaciente", summary = "Crea un nuevo paciente CU2", description = "Crea un nuevo paciente")
    public ResponseEntity<Boolean> create(@Valid @RequestBody PacienteDto pacienteDto) {
        var result = pacienteService.save(pacienteDto.toEntity());
        return result
                .<ResponseEntity<Boolean>>map(success -> ResponseEntity.status(HttpStatus.CREATED).body(success))
                .getOrElseGet(ErrorMapper::mapToResponseEntity);
    }

    @DeleteMapping("/")
    @ApiResponse(responseCode = "2012", content = @Content(schema = @Schema(implementation = Boolean.class)))
    @Operation(operationId = "deletePaciente", summary = "Elimina un paciente CU3", description = "Elimina un paciente")
    public ResponseEntity<Boolean> delete(@RequestParam("id") Integer id) {
        var result = pacienteService.delete(id);
        return result
                .<ResponseEntity<Boolean>>map(success -> ResponseEntity.status(HttpStatus.OK).body(success))
                .getOrElseGet(ErrorMapper::mapToResponseEntity);
    }
}
