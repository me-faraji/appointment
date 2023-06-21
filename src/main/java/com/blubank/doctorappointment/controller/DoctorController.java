package com.blubank.doctorappointment.controller;

import com.blubank.doctorappointment.controller.dto.DTODetailCourse;
import com.blubank.doctorappointment.controller.dto.DTOMasterCourse;
import com.blubank.doctorappointment.controller.excp.ExcpController;
import com.blubank.doctorappointment.controller.excp.ExcpControllerInvalidParameterException;
import com.blubank.doctorappointment.controller.excp.ExcpControllerNullParameterException;
import com.blubank.doctorappointment.model.MasterCourseModel;
import com.blubank.doctorappointment.service.DoctorService;
import com.blubank.doctorappointment.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
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

    @PostMapping(value = "/course/start/{fromDate}/{toDate}")
    public MasterCourseModel addCourse(@PathVariable(name = "fromDate") String strFromDate,
                                       @PathVariable(name = "toDate") String strToDate) throws Exception {
        LOG.info("fromDate: {}, toDate: {}", strFromDate, strToDate);
        if (strFromDate == null || strToDate == null || "".equals(strFromDate.trim()) || "".equals(strToDate.trim()))
            throw new ExcpControllerNullParameterException("وارد کردن پارامترها الزامی می باشد.");
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
    @GetMapping(value = "/course/detail/{date}")
    public DTOMasterCourse getDetailCourseByDate(@PathVariable(name = "date") String strDate) throws Exception {
        LOG.info("strDate: {}", strDate);
        return doctorService.fetchDetailCourseByDate(DateUtil.parse(strDate, DateUtil.EPattern.DD_MM_YYYY));
    }

    @DeleteMapping(name = "/course/detail/delete/{id}")
    public DTODetailCourse deleteDetailCourseById(@PathVariable(name = "id") Long id) {
        return null;
    }

}
