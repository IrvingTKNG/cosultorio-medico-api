package com.example.consultorio_medico_api.external.rest.dto;

import com.example.consultorio_medico_api.core.entity.Paciente;
import com.example.consultorio_medico_api.utils.StringsConstants;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.print.DocFlavor;
import java.time.LocalDate;

@Builder
@Getter
@AllArgsConstructor
@Schema(name = "Paciente" , description = "Contiene información del paciente")
public class PacienteDto {
    @JsonProperty
    @Schema(description = "Identificador del paciente")
    private Integer id;
    @JsonProperty
    @Schema(description = "Nombre del paciente")
    private String nombre;
    @JsonProperty
    @Schema(description = "Apellido paterno del paciente")
    private String apellidoPaterno;
    @JsonProperty
    @Schema(description = "Apellido materno del paciente")
    private String apellidoMaterno;
    @JsonProperty
    @Schema(description = "Numero de teléfono del paciente")
    private String numTelefono;
    @JsonProperty
    @Schema(description = "Numero de teléfono alternativo del paciente")
    private String numTelefonoAlterno;
    @JsonProperty
    @Schema(description = "Correo electronico del paciente")
    private String correo;
    @JsonProperty
    @Schema(description = "Numero de expediente del paciente")
    private String numExpediente;
    @JsonProperty
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = StringsConstants.LOCAL_DATE_FORMAT)
    @Schema(description = "Fecha de nacimiento del paciente", format = "string", implementation = String.class)
    private LocalDate fechaNacimiento;
    @JsonProperty
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = StringsConstants.LOCAL_DATE_FORMAT)
    @Schema(description = "Fecha de registro del paciente", format = "string", implementation = String.class)
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
                .fechaRegistro(entity.getFhRegistro())
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
                .fhRegistro(fechaRegistro)
                .build();
    }
}
