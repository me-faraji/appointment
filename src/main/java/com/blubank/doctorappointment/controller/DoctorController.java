package com.blubank.doctorappointment.controller;

import com.blubank.doctorappointment.controller.dto.DTODetailCourse;
import com.blubank.doctorappointment.controller.dto.DTOStartCourse;
import com.blubank.doctorappointment.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

@RestController(value = "/doctor")
public class DoctorController {
    DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping(value = "/course/start")
    public void startCourse(@RequestBody DTOStartCourse dtoStartCourse) {

    }
    @GetMapping(value = "/course/detail/{date}")
    public List<DTODetailCourse> getDetailCourseByDate(@PathVariable(name = "date") Date date) {
        return null;
    }
    @DeleteMapping(name = "/course/detail/delete/{id}")
    public DTODetailCourse deleteDetailCourseById(@PathVariable(name = "id") Long id) {
        return null;
    }

}
