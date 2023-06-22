package com.blubank.doctorappointment.repository;

import com.blubank.doctorappointment.model.DetailAppointmentModel;
import com.blubank.doctorappointment.repository.custom.DetailAppointmentCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;
import javax.persistence.LockModeType;
import java.util.Optional;

@Repository
public interface DetailAppointmentRepository extends JpaRepository<DetailAppointmentModel, Long>, DetailAppointmentCustomRepository {
    @Override
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<DetailAppointmentModel> findById(Long id);
}
