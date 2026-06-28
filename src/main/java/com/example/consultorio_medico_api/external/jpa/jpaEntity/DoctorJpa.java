package com.example.consultorio_medico_api.external.jpa.jpaEntity;

import com.example.consultorio_medico_api.core.entity.Doctor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "doctor")
public class DoctorJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_doctor")
    private Integer id;
    @Column(name = "tx_nombre")
    private String nombre;
    @Column(name = "tx_apellido_paterno")
    private String apellidoPaterno;
    @Column(name = "tx_apellido_materno")
    private String apellidoMaterno;
    @Column(name = "tx_num_telefono")
    private String numTelefono;
    @Column(name = "tx_num_tel_alter")
    private String numTelefonoAlterno;
    @Column(name = "tx_correo")
    private String correo;
    @Column(name = "tx_cedula")
    private String cedula;
    @Column(name = "tx_especialidad")
    private String especialidad;
    @Column(name = "tm_entrada")
    private LocalTime entrada;
    @Column(name = "tm_salida")
    private LocalTime salida;

    public static DoctorJpa fromEntity(Doctor entity) {
        return DoctorJpa.builder()
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
                .id(id)
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
