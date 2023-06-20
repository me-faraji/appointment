package com.blubank.doctorappointment.repository;

import com.blubank.doctorappointment.model.PatientModel;
import com.blubank.doctorappointment.repository.custom.PatientCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<PatientModel, Long>, PatientCustomRepository {
}
