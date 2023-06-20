package com.blubank.doctorappointment.service;

import com.blubank.doctorappointment.repository.DetailCourseRepository;
import com.blubank.doctorappointment.repository.MasterCourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DoctorService {
    private final MasterCourseRepository masterCourseRepository;
    private final DetailCourseRepository detailCourseRepository;


}
