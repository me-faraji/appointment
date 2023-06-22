package com.blubank.doctorappointment.controller;

import com.blubank.doctorappointment.controller.dto.DTODetailAppointment;
import com.blubank.doctorappointment.controller.dto.DTOMasterAppointment;
import com.blubank.doctorappointment.controller.excp.ExcpControllerInvalidParameterException;
import com.blubank.doctorappointment.model.enums.EDetailAppointmentStatus;
import com.blubank.doctorappointment.service.DoctorService;
import com.blubank.doctorappointment.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/rest/doctor")
public class DoctorController {
    Logger LOG = LoggerFactory.getLogger(DoctorController.class);
    DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping(value = "/appointment/create/{fromDate}/{toDate}")
    public String createAppointment(@PathVariable(name = "fromDate") String strFromDate,
                                    @PathVariable(name = "toDate") String strToDate) throws Exception {
        LOG.info("fromDate: {}, toDate: {}", strFromDate, strToDate);
        long diffInTime;
        Date fromDate;
//        Date to;
        try {
            diffInTime = DateUtil.diffInTime(strFromDate, strToDate, DateUtil.EPattern.DD_MM_YYYY_HH_mm_SS);
            fromDate = DateUtil.parse(strFromDate, DateUtil.EPattern.DD_MM_YYYY_HH_mm_SS);
//            to = DateUtil.parse(toDate, DateUtil.EPattern.DD_MM_YYYY_HH_mm_SS);
        } catch (Exception err) {
            throw new ExcpControllerInvalidParameterException("لطفا پارامترها را صحیح وارد نمائید.");
        }
        if (diffInTime <= 0)
            throw new ExcpControllerInvalidParameterException("از تاریخ بایستی بزرگتر از تا تاریخ باشد.");
        long diffInDays = DateUtil.diffInDays(diffInTime);
        if (diffInDays > 0)
            throw new ExcpControllerInvalidParameterException("حداکثر یک روز قابل زمانبندی می باشد.");
        long diffInMinutes = DateUtil.diffInMinutes(diffInTime);
        if (diffInMinutes < 30)
            throw new ExcpControllerInvalidParameterException("هر دوره حداقل 30 دقیقه می باشد.");
        return doctorService.doSaveAppointment(fromDate, strFromDate, diffInMinutes);
    }
    @GetMapping(value = "/appointment/fetch/{date}")
    public DTOMasterAppointment getAppointment(@PathVariable(name = "date") String strDate) throws Exception {
        LOG.info("strDate: {}", strDate);
        return doctorService.fetchDetail(DateUtil.parse(strDate, DateUtil.EPattern.DD_MM_YYYY));
    }
    @GetMapping(value = "/appointment/empty/fetch/{date}")
    public List<DTODetailAppointment> getEmptyAppointment(@PathVariable(name = "date") String strDate) throws Exception {
        LOG.info("strDate: {}", strDate);
        return doctorService.fetchDetail(DateUtil.parse(strDate, DateUtil.EPattern.DD_MM_YYYY), EDetailAppointmentStatus.EMPTY);
    }
    @GetMapping(value = "/appointment/reserve/fetch/{date}")
    public List<DTODetailAppointment> getReserveAppointment(@PathVariable(name = "date") String strDate) throws Exception {
        LOG.info("strDate: {}", strDate);
        return doctorService.fetchDetail(DateUtil.parse(strDate, DateUtil.EPattern.DD_MM_YYYY), EDetailAppointmentStatus.RESERVE);
    }
    @GetMapping(value = "/appointment/discharge/fetch/{date}")
    public List<DTODetailAppointment> getDischargeAppointment(@PathVariable(name = "date") String strDate) throws Exception {
        LOG.info("strDate: {}", strDate);
        return doctorService.fetchDetail(DateUtil.parse(strDate, DateUtil.EPattern.DD_MM_YYYY), EDetailAppointmentStatus.DISCHARGE);
    }
    @GetMapping(value = "/appointment/delete/fetch/{date}")
    public List<DTODetailAppointment> getDeleteAppointment(@PathVariable(name = "date") String strDate) throws Exception {
        LOG.info("strDate: {}", strDate);
        return doctorService.fetchDetail(DateUtil.parse(strDate, DateUtil.EPattern.DD_MM_YYYY), EDetailAppointmentStatus.DELETE);
    }
    @DeleteMapping(value = "/appointment/delete/{id}")
    public String deleteAppointmentById(@PathVariable(name = "id") Long id) {
        LOG.info("appointment id for delete: {}", id);
        return doctorService.doDeleteAppointmentById(id);
    }

}
