package com.blubank.doctorappointment.service;

import com.blubank.doctorappointment.controller.dto.DTODetailCourse;
import com.blubank.doctorappointment.controller.dto.DTOStartCourse;
import com.blubank.doctorappointment.model.MasterCourseModel;
import com.blubank.doctorappointment.repository.DetailCourseRepository;
import com.blubank.doctorappointment.repository.MasterCourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class DoctorService {
    private final MasterCourseRepository masterCourseRepository;
    private final DetailCourseRepository detailCourseRepository;

    public MasterCourseModel doSaveCourse(DTOStartCourse dtoStartCourse) {
        return null;
    }
    public List<DTODetailCourse> fetchDetailCourseByDate() {
        return null;
    }
    public DTODetailCourse doDeleteDetailCourseById() {
        return null;
    }
}
