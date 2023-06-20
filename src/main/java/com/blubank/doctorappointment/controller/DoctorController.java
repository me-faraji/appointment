package com.blubank.doctorappointment.controller;

import com.blubank.doctorappointment.controller.dto.DTODetailCourse;
import com.blubank.doctorappointment.controller.dto.DTOStartCourse;
import com.blubank.doctorappointment.service.DoctorService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/doctor")
@AllArgsConstructor
public class DoctorController {
    private final DoctorService doctorService;
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
