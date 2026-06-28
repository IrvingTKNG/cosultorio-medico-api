package com.example.consultorio_medico_api.external.rest.controller;

import com.example.consultorio_medico_api.core.business.input.DoctorService;
import com.example.consultorio_medico_api.external.rest.dto.DoctorDto;
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
@RequestMapping("/doctores")
@Tag(name = "Doctores", description = "Endpoints relacionados con los doctores")
public class DoctorController {
    private final DoctorService doctorService;

    @GetMapping("/")
    @ApiResponse(responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = DoctorDto.class))))
    @Operation(operationId = "listAllDoctores", summary = "Obtiene todos los doctores.CU-DOC-01", description = "Obtiene todos los doctores registrados")
    public ResponseEntity<List<DoctorDto>> listAllDoctores() {
        var respuesta = doctorService.listAll().stream().map(DoctorDto::fromEntity).toList();
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/{idDoctor}")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = DoctorDto.class)))
    @Operation(operationId = "getDoctor", summary = "Obtiene un doctor por su id.CU-DOC-02", description = "Obtiene un doctor por su id")
    public ResponseEntity<DoctorDto> getDoctor(@PathVariable Integer idDoctor) {
        var respuesta = doctorService.findById(idDoctor).map(DoctorDto::fromEntity);
        return respuesta.fold(ErrorMapper::mapToResponseEntity, ResponseEntity::ok);
    }

    @PostMapping("/")
    @ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = Boolean.class)))
    @Operation(operationId = "createDoctor", summary = "Crea un doctor.CU-DOC-03", description = "Crea un doctor")
    public ResponseEntity<Boolean> createDoctor(@Valid @RequestBody DoctorDto doctorDto) {
        var respuesta = doctorService.create(doctorDto.toEntity());
        return respuesta.fold(
                ErrorMapper::mapToResponseEntity,
                success -> ResponseEntity.status(HttpStatus.CREATED).body(success)
        );
    }

    @DeleteMapping("/{idDoctor}")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = Boolean.class)))
    @Operation(operationId = "deleteDoctor", summary = "Elimina un doctor.CU-DOC-04", description = "Elimina un doctor")
    public ResponseEntity<Boolean> deleteDoctor(@PathVariable Integer idDoctor) {
        var respuesta = doctorService.delete(idDoctor);
        return respuesta.fold(ErrorMapper::mapToResponseEntity, ResponseEntity::ok);
    }

    @PutMapping("/{idDoctor}")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = Boolean.class)))
    @Operation(operationId = "updateDoctor", summary = "Actualiza un doctor.CU-DOC-05", description = "Actualiza un doctor")
    public ResponseEntity<Boolean> updateDoctor(@PathVariable Integer idDoctor, @Valid @RequestBody DoctorDto doctorDto) {
        var respuesta = doctorService.update(idDoctor, doctorDto.toEntity());
        return respuesta.fold(ErrorMapper::mapToResponseEntity, ResponseEntity::ok);
    }
}
