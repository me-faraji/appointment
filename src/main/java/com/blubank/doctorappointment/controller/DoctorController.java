package com.blubank.doctorappointment.controller;

import com.blubank.doctorappointment.controller.dto.DTODetailCourse;
import com.blubank.doctorappointment.controller.dto.DTOStartCourse;
import com.blubank.doctorappointment.service.DoctorService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/rest/doctor")
public class DoctorController {
    DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping(value = "/course/start/{fromDate}/{toDate}")
    public void addCourse(@PathVariable(name = "fromDate") String fromDate, @PathVariable(name = "toDate") String toDate) {

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
