package com.blubank.doctorappointment.repository;

import com.blubank.doctorappointment.model.MasterCourseModel;
import com.blubank.doctorappointment.repository.custom.MasterCourseCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.Optional;

@Repository
public interface MasterCourseRepository extends JpaRepository<MasterCourseModel, Long>, MasterCourseCustomRepository {

//    @Query("select m from MasterCourseModel m where m.fromDate <= ?1 or m.toDate >= ?1")
//    @EntityGraph(value = "MasterCourseModel.detail")
//    @EntityGraph(attributePaths = {"detail"})
    Optional<MasterCourseModel> findByDate(Date fromDate);

}
