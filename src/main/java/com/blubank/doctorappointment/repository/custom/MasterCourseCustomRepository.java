package com.blubank.doctorappointment.repository.custom;

import com.blubank.doctorappointment.model.MasterCourseModel;

import java.util.Date;
import java.util.Optional;

public interface MasterCourseCustomRepository {
    Optional<MasterCourseModel> fetchByFromDate(Date fromDate) throws Exception;
}
