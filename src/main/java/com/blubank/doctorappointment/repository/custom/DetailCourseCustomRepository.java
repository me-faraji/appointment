package com.blubank.doctorappointment.repository.custom;

import com.blubank.doctorappointment.model.DetailCourseModel;
import com.blubank.doctorappointment.model.MasterCourseModel;

import java.util.List;

public interface DetailCourseCustomRepository {
    List<DetailCourseModel> findByMaster(MasterCourseModel masterCourseModel) throws Exception;
}
