package com.blubank.doctorappointment.service;

import com.blubank.doctorappointment.controller.dto.DTODetailCourse;
import com.blubank.doctorappointment.controller.dto.DTOReserve;
import com.blubank.doctorappointment.repository.DetailCourseRepository;
import com.blubank.doctorappointment.repository.MasterCourseRepository;
import com.blubank.doctorappointment.repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    MasterCourseRepository masterCourseRepository;
    DetailCourseRepository detailCourseRepository;
    PatientRepository patientRepository;

    @Autowired
    public PatientService(MasterCourseRepository masterCourseRepository,
                          DetailCourseRepository detailCourseRepository,
                          PatientRepository patientRepository) {
        this.masterCourseRepository = masterCourseRepository;
        this.detailCourseRepository = detailCourseRepository;
        this.patientRepository = patientRepository;

    }

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
