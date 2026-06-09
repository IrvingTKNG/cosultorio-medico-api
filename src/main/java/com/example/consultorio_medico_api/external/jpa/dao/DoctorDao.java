package com.example.consultorio_medico_api.external.jpa.dao;

import com.example.consultorio_medico_api.core.business.output.DoctorRepository;
import com.example.consultorio_medico_api.core.entity.Doctor;
import com.example.consultorio_medico_api.external.jpa.jpaEntity.DoctorJpa;
import com.example.consultorio_medico_api.external.jpa.repository.DoctorJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class DoctorDao implements DoctorRepository {
    private final DoctorJpaRepository doctorJpaRepository;

    @Override
    public Optional<Doctor> findById(Integer id) {
        var doctor = doctorJpaRepository.findById(id);
        return doctor.map(DoctorJpa::toEntity);
    }

    @Override
    public Optional<Doctor> findByCedula(String cedula) {
        var doctor = doctorJpaRepository.findByCedula(cedula);
        return doctor.map(DoctorJpa::toEntity);
    }

    @Override
    public List<Doctor> findAll() {
        var doctorList = doctorJpaRepository.findAll();
        return doctorList.stream().map(DoctorJpa::toEntity).toList();
    }

    @Override
    public void save(Doctor doctor) {
        doctorJpaRepository.save(DoctorJpa.fromEntity(doctor));
    }

    @Override
    public void deleteById(Integer id) {
        doctorJpaRepository.deleteById(id);
    }
}
