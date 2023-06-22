package com.blubank.doctorappointment.controller;

import com.blubank.doctorappointment.controller.dto.DTODetailAppointment;
import com.blubank.doctorappointment.controller.dto.DTOPatient;
import com.blubank.doctorappointment.controller.dto.DTOReserve;
import com.blubank.doctorappointment.controller.excp.ExcpControllerInvalidParameterException;
import com.blubank.doctorappointment.model.enums.EDetailAppointmentStatus;
import com.blubank.doctorappointment.service.DoctorService;
import com.blubank.doctorappointment.service.PatientService;
import com.blubank.doctorappointment.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/appointment/empty/fetch/{strDate}")
    public List<DTODetailAppointment> getEmptyAppointment(@PathVariable(name = "strDate") String strDate) throws Exception {
        LOG.info("strDate: {}", strDate);
        return doctorService.fetchDetail(DateUtil.parse(strDate, DateUtil.EPattern.DD_MM_YYYY), EDetailAppointmentStatus.EMPTY);
    }
    @GetMapping(value = "/appointment/reserve/fetch/{mobil}")
    public DTOPatient getReserveAppointment(@PathVariable(name = "mobil") String mobil) throws Exception {
        LOG.info("mobil: {}", mobil);
        return patientService.fetchReserveAppointment(mobil);
    }
    @PostMapping(value = "/appointment/reserve")
    public String reserveAppointment(@RequestBody DTOReserve dtoReserve) {
        if (dtoReserve.getAppointmentId() == null
                || dtoReserve.getFirstName() == null
                || dtoReserve.getLastName() == null
                || dtoReserve.getMobil() == null
        ) throw new ExcpControllerInvalidParameterException("لطفا پارامترها را صحیح وارد نمائید.");
        return patientService.doReserveAppointment(dtoReserve);
    }
}
