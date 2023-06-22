package com.blubank.doctorappointment.controller;

import com.blubank.doctorappointment.controller.dto.DTODetailCourse;
import com.blubank.doctorappointment.controller.dto.DTOMasterCourse;
import com.blubank.doctorappointment.controller.excp.ExcpControllerInvalidParameterException;
import com.blubank.doctorappointment.controller.excp.ExcpControllerNullParameterException;
import com.blubank.doctorappointment.model.MasterCourseModel;
import com.blubank.doctorappointment.model.enums.EDetailCourseStatus;
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

    @PostMapping(value = "/course/create/{fromDate}/{toDate}")
    public String createCourse(@PathVariable(name = "fromDate") String strFromDate,
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
        return doctorService.doSaveCourse(fromDate, strFromDate, diffInMinutes);
    }
    @GetMapping(value = "/course/fetch/{date}")
    public DTOMasterCourse getCourse(@PathVariable(name = "date") String strDate) throws Exception {
        LOG.info("strDate: {}", strDate);
        return doctorService.fetchCourse(DateUtil.parse(strDate, DateUtil.EPattern.DD_MM_YYYY));
    }
    @GetMapping(value = "/course/empty/fetch/{date}")
    public List<DTODetailCourse> getEmptyCourse(@PathVariable(name = "date") String strDate) throws Exception {
        LOG.info("strDate: {}", strDate);
        return doctorService.fetchCourse(DateUtil.parse(strDate, DateUtil.EPattern.DD_MM_YYYY), EDetailCourseStatus.EMPTY);
    }
    @GetMapping(value = "/course/reserve/fetch/{date}")
    public List<DTODetailCourse> getReserveCourse(@PathVariable(name = "date") String strDate) throws Exception {
        LOG.info("strDate: {}", strDate);
        return doctorService.fetchCourse(DateUtil.parse(strDate, DateUtil.EPattern.DD_MM_YYYY), EDetailCourseStatus.RESERVE);
    }
    @GetMapping(value = "/course/discharge/fetch/{date}")
    public List<DTODetailCourse> getDischargeCourse(@PathVariable(name = "date") String strDate) throws Exception {
        LOG.info("strDate: {}", strDate);
        return doctorService.fetchCourse(DateUtil.parse(strDate, DateUtil.EPattern.DD_MM_YYYY), EDetailCourseStatus.DISCHARGE);
    }
    @GetMapping(value = "/course/delete/fetch/{date}")
    public List<DTODetailCourse> getDeleteCourse(@PathVariable(name = "date") String strDate) throws Exception {
        LOG.info("strDate: {}", strDate);
        return doctorService.fetchCourse(DateUtil.parse(strDate, DateUtil.EPattern.DD_MM_YYYY), EDetailCourseStatus.DELETE);
    }
    @DeleteMapping(value = "/course/delete/{id}")
    public String deleteCourseById(@PathVariable(name = "id") Long id) {
        LOG.info("course id for delete: {}", id);
        return doctorService.doDeleteCourseById(id);
    }

}
