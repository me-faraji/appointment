package com.blubank.doctorappointment.controller;

import com.blubank.doctorappointment.controller.dto.DTODetailCourse;
import com.blubank.doctorappointment.controller.dto.DTOReserve;
import com.blubank.doctorappointment.service.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/rest/patient")
public class PatientController {
    PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping(value = "/course/empty/list/{date}")
    public List<DTODetailCourse> getCourseEmptyByDate(@PathVariable(name = "date") Date date) {
        return null;
    }
    @PostMapping(name = "/course/reserve")
    public DTOReserve reserveCourse(@RequestBody DTOReserve dtoReserve) {
        return null;
    }
    @GetMapping(name = "/course/reserve/list/{mobile}")
    public List<DTODetailCourse> getAllCourse(@PathVariable(name = "mobile") String mobile) {
        return null;
    }
}
