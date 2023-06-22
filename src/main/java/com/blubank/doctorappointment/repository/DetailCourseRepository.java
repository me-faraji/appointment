package com.blubank.doctorappointment.repository;

import com.blubank.doctorappointment.model.DetailCourseModel;
import com.blubank.doctorappointment.repository.custom.DetailCourseCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;
import javax.persistence.LockModeType;
import java.util.Optional;

@Repository
public interface DetailCourseRepository extends JpaRepository<DetailCourseModel, Long>, DetailCourseCustomRepository {
    @Override
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<DetailCourseModel> findById(Long id);
}
