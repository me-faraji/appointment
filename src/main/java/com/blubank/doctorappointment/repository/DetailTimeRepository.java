package com.blubank.doctorappointment.repository;

import com.blubank.doctorappointment.model.DetailTimeModel;
import com.blubank.doctorappointment.repository.custom.DetailTimeCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailTimeRepository extends JpaRepository<DetailTimeModel, Long>, DetailTimeCustomRepository {
}
