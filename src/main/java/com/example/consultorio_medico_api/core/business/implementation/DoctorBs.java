package com.example.consultorio_medico_api.core.business.implementation;

import com.example.consultorio_medico_api.core.business.input.DoctorService;
import com.example.consultorio_medico_api.core.business.output.DoctorRepository;
import com.example.consultorio_medico_api.core.entity.Doctor;
import com.example.consultorio_medico_api.utils.error.ErrorBs;
import com.example.consultorio_medico_api.utils.error.ErrorEnum;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DoctorBs implements DoctorService {
    private final DoctorRepository doctorRepository;

    @Override
    public List<Doctor> listAll() {
        return doctorRepository.findAll();
    }

    @Override
    public Either<ErrorBs, Doctor> findById(Integer id) {
        var optionalDoctor = doctorRepository.findById(id);
        return optionalDoctor.<Either<ErrorBs, Doctor>>map(Either::right).orElseGet(() -> Either.left(ErrorEnum.NOT_FOUND));
    }

    @Override
    @Transactional
    public Either<ErrorBs, Boolean> save(Doctor doctor) {
        var doctorExistente = doctorRepository.findByCedula(doctor.getCedula());
        if (doctorExistente.isPresent()) {
            return Either.left(ErrorEnum.USER_DUPLICATED);
        }
        doctorRepository.save(doctor);
        return Either.right(Boolean.TRUE);
    }

    @Override
    @Transactional
    public Either<ErrorBs, Boolean> delete(Integer id) {
        var doctorExists = doctorRepository.existsById(id);
        if (doctorExists.equals(false)) {
            return Either.left(ErrorEnum.NOT_FOUND);
        }
        doctorRepository.deleteById(id);
        return Either.right(Boolean.TRUE);
    }

    @Override
    @Transactional
    public Either<ErrorBs, Boolean> update(Integer id, Doctor doctor) {
        var optionalDoctor = doctorRepository.findById(id);
        if (optionalDoctor.isEmpty()) {
            return Either.left(ErrorEnum.NOT_FOUND);
        }
        var getDoctor = optionalDoctor.get();
        getDoctor.setNombre(doctor.getNombre());
        getDoctor.setApellidoPaterno(doctor.getApellidoPaterno());
        getDoctor.setApellidoMaterno(doctor.getApellidoMaterno());
        getDoctor.setCorreo(doctor.getCorreo());
        getDoctor.setCedula(doctor.getCedula());
        getDoctor.setEspecialidad(doctor.getEspecialidad());
        getDoctor.setEntrada(doctor.getEntrada());
        getDoctor.setSalida(doctor.getSalida());
        doctorRepository.save(getDoctor);
        return Either.right(Boolean.TRUE);
    }
}
