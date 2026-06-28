package com.example.consultorio_medico_api.core.business.input;

import com.example.consultorio_medico_api.core.entity.Doctor;
import com.example.consultorio_medico_api.utils.error.ErrorBs;
import io.vavr.control.Either;

import java.util.List;

public interface DoctorService {
    /***
     * Trae todos los doctores
     * @return
     */
    List<Doctor> listAll();

    /***
     * Trae un doctor por su id
     * @param id
     * @return
     */
    Either<ErrorBs, Doctor> findById(Integer id);

    /***
     * Guarda un doctor
     * @param doctor
     * @return
     */
    Either<ErrorBs, Boolean> create(Doctor doctor);

    /***
     * Borra un doctor por su id
     * @param id
     * @return
     */
    Either<ErrorBs, Boolean> delete(Integer id);

    /***
     * Actualiza un doctor
     * @param id
     * @param doctor
     * @return
     */
    Either<ErrorBs, Boolean> update(Integer id, Doctor doctor);

}
