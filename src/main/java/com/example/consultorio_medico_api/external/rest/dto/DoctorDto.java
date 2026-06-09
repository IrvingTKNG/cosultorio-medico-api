package com.example.consultorio_medico_api.external.rest.dto;

import com.example.consultorio_medico_api.core.entity.Doctor;
import com.example.consultorio_medico_api.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Doctor", description = "Contiene información del doctor")
public class DoctorDto {
    @JsonProperty
    @Schema(description = "Identificador del doctor", accessMode = Schema.AccessMode.READ_ONLY)
    private Integer id;
    @JsonProperty
    @NotBlank
    @Schema(description = "Nombre del doctor", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nombre;
    @JsonProperty
    @NotBlank
    @Schema(description = "Apellido paterno del doctor", requiredMode = Schema.RequiredMode.REQUIRED)
    private String apellidoPaterno;
    @JsonProperty
    @Schema(description = "Apellido materno del doctor", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String apellidoMaterno;
    @JsonProperty
    @NotBlank
    @Schema(description = "Numero de teléfono del doctor", requiredMode = Schema.RequiredMode.REQUIRED)
    private String numTelefono;
    @JsonProperty
    @Schema(description = "Numero de teléfono alternativo del doctor", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String numTelefonoAlterno;
    @JsonProperty
    @NotBlank
    @Schema(description = "Correo electrónico del doctor", requiredMode = Schema.RequiredMode.REQUIRED)
    private String correo;
    @JsonProperty
    @NotBlank
    @Schema(description = "Cedula del doctor", requiredMode = Schema.RequiredMode.REQUIRED)
    private String cedula;
    @JsonProperty
    @NotBlank
    @Schema(description = "Especialidad del doctor", requiredMode = Schema.RequiredMode.REQUIRED)
    private String especialidad;
    @JsonProperty
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.TIME_FORMAT)
    @Schema(description = "Hora de entrada del doctor", requiredMode = Schema.RequiredMode.REQUIRED,type = "string", example = "15:00:00")
    private LocalTime entrada;
    @JsonProperty
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateUtils.TIME_FORMAT)
    @Schema(description = "Hora de salida del doctor", requiredMode = Schema.RequiredMode.REQUIRED,type = "string", example = "15:00:00")
    private LocalTime salida;

    public static DoctorDto fromEntity(Doctor entity) {
        return DoctorDto.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .apellidoPaterno(entity.getApellidoPaterno())
                .apellidoMaterno(entity.getApellidoMaterno())
                .numTelefono(entity.getNumTelefono())
                .numTelefonoAlterno(entity.getNumTelefonoAlterno())
                .correo(entity.getCorreo())
                .cedula(entity.getCedula())
                .especialidad(entity.getEspecialidad())
                .entrada(entity.getEntrada())
                .salida(entity.getSalida())
                .build();
    }

    public Doctor toEntity() {
        return Doctor.builder()
                .nombre(nombre)
                .apellidoPaterno(apellidoPaterno)
                .apellidoMaterno(apellidoMaterno)
                .numTelefono(numTelefono)
                .numTelefonoAlterno(numTelefonoAlterno)
                .correo(correo)
                .cedula(cedula)
                .especialidad(especialidad)
                .entrada(entrada)
                .salida(salida)
                .build();
    }
}
