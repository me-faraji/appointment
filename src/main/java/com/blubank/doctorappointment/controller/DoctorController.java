package com.blubank.doctorappointment.controller;

import com.blubank.doctorappointment.controller.dto.DTODetailCourse;
import com.blubank.doctorappointment.controller.excp.ExcpController;
import com.blubank.doctorappointment.model.MasterCourseModel;
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

    @PostMapping(value = "/course/start/{fromDate}/{toDate}")
    public MasterCourseModel addCourse(@PathVariable(name = "fromDate") String fromDate, @PathVariable(name = "toDate") String toDate) {
        LOG.info("fromDate: {}, toDate: {}", fromDate, toDate);
        if (fromDate == null || toDate == null || "".equals(fromDate.trim()) || "".equals(toDate.trim()))
            throw new ExcpController(400, "Bad Request", "وارد کردن پارامترها الزامی می باشد.");
        long diffInTime;
        Date from;
        Date to;
        try {
            diffInTime = DateUtil.diffInTime(fromDate, toDate);
            from = DateUtil.toDate(fromDate);
            to = DateUtil.toDate(toDate);
        } catch (Exception err) {
            throw new ExcpController(400, "Bad Request", "لطفا پارامترها را صحیح وارد نمائید.");
        }
        if (diffInTime <= 0)
            throw new ExcpController(400, "Bad Request", "از تاریخ بایستی بزرگتر از تا تاریخ باشد.");
        long diffInDays = DateUtil.diffInDays(diffInTime);
        if (diffInDays > 0)
            throw new ExcpController(400, "Bad Request", "حداکثر یک روز قابل زمانبندی می باشد.");
        long diffInMinutes = DateUtil.diffInMinutes(diffInTime);
        if (diffInMinutes < 30)
            throw new ExcpController(400, "Bad Request", "هر دوره حداقل 30 دقیقه می باشد.");
        System.out.println(DateUtil.plusMinute(from, 30));
        return null;//doctorService.doSaveCourse(from, to, diffInMinutes);
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
