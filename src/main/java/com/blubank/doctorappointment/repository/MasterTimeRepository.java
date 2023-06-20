package com.blubank.doctorappointment.repository;

import com.blubank.doctorappointment.repository.custom.MasterTimeCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterTimeRepository extends JpaRepository<MasterTimeRepository, Long>, MasterTimeCustomRepository {
}
