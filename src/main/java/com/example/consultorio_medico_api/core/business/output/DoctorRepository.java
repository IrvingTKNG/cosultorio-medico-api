package com.example.consultorio_medico_api.core.business.output;

import com.example.consultorio_medico_api.core.entity.Doctor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository {
    /***
     * Verifica si existe un doctor por su id
     * @param id
     * @return
     */
    Boolean existsById(Integer id);

    /***
     * Trae un doctor por su id
     * @param id
     * @return
     */
    Optional<Doctor> findById(Integer id);

    /***
     * Trae un doctor por su cedula
     * @param cedula
     * @return
     */
    Optional<Doctor> findByCedula(String cedula);

    /***
     * Trae todos los doctores
     * @return
     */
    List<Doctor> findAll();

    /***
     * Guarda un doctor
     * @param doctor
     */
    void save(Doctor doctor);

    /***
     * Borra un doctor por su id
     * @param id
     */
    void deleteById(Integer id);
}
