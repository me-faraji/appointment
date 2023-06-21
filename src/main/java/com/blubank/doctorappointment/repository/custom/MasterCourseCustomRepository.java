package com.blubank.doctorappointment.repository.custom;

import com.blubank.doctorappointment.model.DetailCourseModel;
import com.blubank.doctorappointment.model.MasterCourseModel;
import org.jinq.tuples.Pair;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface MasterCourseCustomRepository {
    List<DetailCourseModel> findByDateAndStatusCourse(Date date, int status) throws Exception;
}
