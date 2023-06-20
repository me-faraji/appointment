package com.blubank.doctorappointment.service;

import com.blubank.doctorappointment.repository.DetailCourseRepository;
import com.blubank.doctorappointment.repository.MasterCourseRepository;
import com.blubank.doctorappointment.repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PatientService {
    private final MasterCourseRepository masterCourseRepository;
    private final DetailCourseRepository detailCourseRepository;
    private final PatientRepository patientRepository;
    
}
