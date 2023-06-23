package com.blubank.doctorappointment.repository;

import com.blubank.doctorappointment.model.PatientModel;
import com.blubank.doctorappointment.repository.custom.PatientCustomRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<PatientModel, Long>, PatientCustomRepository {
//    @Cacheable(value = "patient", key = "#mobil")
    Optional<PatientModel> findByMobil(String mobil);
}
