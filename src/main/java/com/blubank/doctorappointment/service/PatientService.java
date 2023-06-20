package com.blubank.doctorappointment.service;

import com.blubank.doctorappointment.controller.dto.DTODetailCourse;
import com.blubank.doctorappointment.controller.dto.DTOReserve;
import com.blubank.doctorappointment.repository.DetailCourseRepository;
import com.blubank.doctorappointment.repository.MasterCourseRepository;
import com.blubank.doctorappointment.repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PatientService {
    private final MasterCourseRepository masterCourseRepository;
    private final DetailCourseRepository detailCourseRepository;
    private final PatientRepository patientRepository;

    public List<DTODetailCourse> fetchCourseEmptyByDate() {
        return null;
    }
    public DTOReserve doReserveCourse() {
        return null;
    }

    public List<DTODetailCourse> fetchAllCourse() {
        return null;
    }
}
