package com.blubank.doctorappointment.repository;

import com.blubank.doctorappointment.model.MasterAppointmentModel;
import com.blubank.doctorappointment.repository.custom.MasterAppointmentCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.Optional;

@Repository
public interface MasterAppointmentRepository extends JpaRepository<MasterAppointmentModel, Long>, MasterAppointmentCustomRepository {

//    @Query("select m from MasterCourseModel m where m.fromDate <= ?1 or m.toDate >= ?1")
//    @EntityGraph(value = "MasterCourseModel.detail")
//    @EntityGraph(attributePaths = {"detail"})
    Optional<MasterAppointmentModel> findByDate(Date fromDate);

}
