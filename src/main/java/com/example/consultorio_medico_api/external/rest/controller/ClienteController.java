package com.example.consultorio_medico_api.external.rest.controller;

import com.example.consultorio_medico_api.core.business.input.PacienteService;
import com.example.consultorio_medico_api.external.rest.dto.PacienteDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
