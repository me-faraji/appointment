package com.blubank.doctorappointment.controller;

import com.blubank.doctorappointment.controller.dto.DTODetailCourse;
import com.blubank.doctorappointment.controller.dto.DTOPatient;
import com.blubank.doctorappointment.controller.dto.DTOReserve;
import com.blubank.doctorappointment.controller.excp.ExcpControllerInvalidParameterException;
import com.blubank.doctorappointment.model.enums.EDetailCourseStatus;
import com.blubank.doctorappointment.service.DoctorService;
import com.blubank.doctorappointment.service.PatientService;
import com.blubank.doctorappointment.util.DateUtil;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/rest/patient")
public class PatientController {
    Logger LOG = LoggerFactory.getLogger(PatientController.class);
    PatientService patientService;
    DoctorService doctorService;

    @Autowired
    public PatientController(PatientService patientService, DoctorService doctorService) {
        this.patientService = patientService;
        this.doctorService = doctorService;
    }

    @GetMapping(value = "/course/empty/fetch/{strDate}")
    public List<DTODetailCourse> getEmptyCourse(@PathVariable(name = "strDate") String strDate) throws Exception {
        LOG.info("strDate: {}", strDate);
        return doctorService.fetchCourse(DateUtil.parse(strDate, DateUtil.EPattern.DD_MM_YYYY), EDetailCourseStatus.EMPTY);
    }
    @GetMapping(value = "/course/reserve/fetch/{mobil}")
    public DTOPatient getReserveCourse(@PathVariable(name = "mobil") String mobil) throws Exception {
        LOG.info("mobil: {}", mobil);
        return patientService.fetchReserveCourse(mobil);
    }
    @PostMapping(value = "/course/reserve")
    public String reserveCourse(@RequestBody DTOReserve dtoReserve) {
        if (dtoReserve.getCourseId() == null
                || dtoReserve.getFirstName() == null
                || dtoReserve.getLastName() == null
                || dtoReserve.getMobil() == null
        ) throw new ExcpControllerInvalidParameterException("لطفا پارامترها را صحیح وارد نمائید.");
        return patientService.doReserveCourse(dtoReserve);
    }
}
