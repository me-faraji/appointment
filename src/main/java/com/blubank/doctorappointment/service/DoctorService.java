package com.blubank.doctorappointment.service;

import com.blubank.doctorappointment.controller.dto.DTODetailCourse;
import com.blubank.doctorappointment.controller.dto.DTOStartCourse;
import com.blubank.doctorappointment.model.MasterCourseModel;
import com.blubank.doctorappointment.repository.DetailCourseRepository;
import com.blubank.doctorappointment.repository.MasterCourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class DoctorService {
    MasterCourseRepository masterCourseRepository;
    DetailCourseRepository detailCourseRepository;

    @Autowired
    public DoctorService(MasterCourseRepository masterCourseRepository,
                         DetailCourseRepository detailCourseRepository) {
        this.masterCourseRepository = masterCourseRepository;
        this.detailCourseRepository = detailCourseRepository;
    }

    @Transactional
    public MasterCourseModel doSaveCourse(Date from, Date to, long diffInMinutes) {

        return null;
    }
    public List<DTODetailCourse> fetchDetailCourseByDate() {
        return null;
    }
    public DTODetailCourse doDeleteDetailCourseById() {
        return null;
    }
}
