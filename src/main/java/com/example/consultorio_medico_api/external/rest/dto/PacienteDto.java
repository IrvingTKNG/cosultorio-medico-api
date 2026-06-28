package com.example.consultorio_medico_api.external.rest.dto;

import com.example.consultorio_medico_api.core.entity.Paciente;
import com.example.consultorio_medico_api.utils.StringsConstants;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
@AllArgsConstructor
@Schema(name = "Paciente", description = "Contiene información del paciente")
public class PacienteDto {
    @JsonProperty
    @Schema(description = "Identificador del paciente", accessMode = Schema.AccessMode.READ_ONLY)
    private Integer id;
    @JsonProperty
    @Schema(description = "Nombre del paciente",requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    private String nombre;
    @JsonProperty
    @Schema(description = "Apellido paterno del paciente",requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    private String apellidoPaterno;
    @JsonProperty
    @Schema(description = "Apellido materno del paciente")
    private String apellidoMaterno;
    @JsonProperty
    @Schema(description = "Numero de teléfono del paciente",requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    private String numTelefono;
    @JsonProperty
    @Schema(description = "Numero de teléfono alternativo del paciente")
    private String numTelefonoAlterno;
    @JsonProperty
    @Schema(description = "Correo electrónico del paciente",requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    private String correo;
    @JsonProperty
    @Schema(description = "Numero de expediente del paciente")
    private String numExpediente;
    @JsonProperty
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = StringsConstants.LOCAL_DATE_FORMAT)
    @Schema(description = "Fecha de nacimiento del paciente", format = "string", implementation = String.class, example = "01/01/2010",requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private LocalDate fechaNacimiento;
    @JsonProperty
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = StringsConstants.LOCAL_DATE_FORMAT)
    @Schema(description = "Fecha de registro del paciente", format = "string", implementation = String.class, accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDate fechaRegistro;

    public static PacienteDto fromEntity(Paciente entity) {
        return PacienteDto.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .apellidoPaterno(entity.getApellidoPaterno())
                .apellidoMaterno(entity.getApellidoMaterno())
                .numTelefono(entity.getNumTelefono())
                .numTelefonoAlterno(entity.getNumTelefonoAlterno())
                .correo(entity.getCorreo())
                .numExpediente(entity.getNumExpediente())
                .fechaNacimiento(entity.getFhNacimiento())
                .fechaRegistro(entity.getFhRegistro().toLocalDate())
                .build();
    }

    public Paciente toEntity() {
        return Paciente.builder()
                .nombre(nombre)
                .apellidoPaterno(apellidoPaterno)
                .apellidoMaterno(apellidoMaterno)
                .numTelefono(numTelefono)
                .numTelefonoAlterno(numTelefonoAlterno)
                .correo(correo)
                .numExpediente(numExpediente)
                .fhNacimiento(fechaNacimiento)
                .build();
    }
}
