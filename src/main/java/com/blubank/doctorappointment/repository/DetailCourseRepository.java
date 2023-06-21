package com.blubank.doctorappointment.repository;

import com.blubank.doctorappointment.model.DetailCourseModel;
import com.blubank.doctorappointment.model.MasterCourseModel;
import com.blubank.doctorappointment.repository.custom.DetailCourseCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface DetailCourseRepository extends JpaRepository<DetailCourseModel, Long>, DetailCourseCustomRepository {

}
