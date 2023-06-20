package com.blubank.doctorappointment.repository;

import com.blubank.doctorappointment.model.MasterCourseModel;
import com.blubank.doctorappointment.repository.custom.MasterCourseCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterCourseRepository extends JpaRepository<MasterCourseModel, Long>, MasterCourseCustomRepository {
}
