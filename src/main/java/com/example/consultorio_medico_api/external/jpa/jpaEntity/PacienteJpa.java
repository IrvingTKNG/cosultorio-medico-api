package com.example.consultorio_medico_api.external.jpa.jpaEntity;

import com.example.consultorio_medico_api.core.entity.Paciente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "paciente")
public class PacienteJpa {
    @Id
    @SequenceGenerator(name = "paciente_id_seq", sequenceName = "paciente_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "paciente_id_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id_paciente")
    private Integer id;
    @Column(name = "tx_nombre")
    private String nombre;
    @Column(name = "tx_apellido_paterno")
    private String apellidoPaterno;
    @Column(name = "tx_apellido_materno")
    private String apellidoMaterno;
    @Column(name = "tx_num_telefono")
    private String numTelefono;
    @Column(name = "tx_num_telefono_alter")
    private String numTelefonoAlterno;
    @Column(name = "tx_correo")
    private String correo;
    @Column(name = "tx_num_expediente")
    private String numExpediente;
    @Column(name = "fh_nacimiento")
    private LocalDate fechaNacimiento;
    @Column(name = "fh_registro")
    private LocalDate fechaRegistro;

    public static PacienteJpa fromEntity(Paciente entity) {
        return PacienteJpa.builder()
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
        .id(id)
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
