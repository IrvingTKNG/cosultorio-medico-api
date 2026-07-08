package com.example.consultorio_medico_api.external.rest.controller;

import com.example.consultorio_medico_api.core.business.input.RecetaService;
import com.example.consultorio_medico_api.external.rest.dto.RecetaDto;
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
@RequestMapping("/receta")
@Tag(name = "receta", description = "Endpoints relacionados a las recetas")
public class RecetaController {
    private final RecetaService recetaService;

    @GetMapping("/")
    @ApiResponse(responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = RecetaDto.class))))
    @Operation(operationId = "findAllRecetas", summary = "Obtiene todos las recetas. CU-REC-01", description = "Obtiene todos las recetas registrados en el sistema")
    public ResponseEntity<List<RecetaDto>> findAllRecetas() {
        var respuesta = recetaService.listAll().stream().map(RecetaDto::fromEntity)
                .toList();
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/{idReceta}")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = RecetaDto.class)))
    @Operation(operationId = "getReceta", summary = "Obtiene una receta por su id.CU-REC-02", description = "Obtiene una receta por su id")
    public ResponseEntity<RecetaDto> getReceta(@PathVariable Integer idReceta) {
        var respuesta = recetaService.getById(idReceta).map(RecetaDto::fromEntity);
        return respuesta.fold(ErrorMapper::mapToResponseEntity, ResponseEntity::ok);
    }

    @PostMapping("/{idConsulta}")
    @ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = Boolean.class)))
    @Operation(operationId = "createReceta", summary = "Crea una nueva receta. CU-REC-03", description = "Crea una nueva receta")
    public ResponseEntity<Boolean> createReceta(@Valid @RequestBody RecetaDto recetaDto, @PathVariable Integer idConsulta) {
        var result = recetaService.create(recetaDto.toEntity(), idConsulta);
        return result.fold(ErrorMapper::mapToResponseEntity,
                success -> ResponseEntity.status(HttpStatus.CREATED).body(success));
    }

    @DeleteMapping("/{idReceta}")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = Boolean.class)))
    @Operation(operationId = "deleteReceta", summary = "Elimina una receta.CU-REC-04", description = "Elimina una receta")
    public ResponseEntity<Boolean> deleteReceta(@PathVariable Integer idReceta) {
        var respuesta = recetaService.delete(idReceta);
        return respuesta.fold(ErrorMapper::mapToResponseEntity, ResponseEntity::ok);
    }


    @PutMapping("/{idReceta}")
    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = Boolean.class)))
    @Operation(operationId = "idReceta", summary = "Actualiza una receta.CU-REC-05", description = "Actualiza una receta")
    public ResponseEntity<Boolean> idReceta(@PathVariable Integer idReceta, @Valid @RequestBody RecetaDto recetaDto) {
        var respuesta = recetaService.update(idReceta, recetaDto.toEntity());
        return respuesta.fold(ErrorMapper::mapToResponseEntity, ResponseEntity::ok);
    }

}
