package com.blubank.doctorappointment.common;

import com.blubank.doctorappointment.model.DetailCourseModel;
import com.blubank.doctorappointment.model.MasterCourseModel;
import com.blubank.doctorappointment.model.enums.EDetailCourseStatus;
import com.blubank.doctorappointment.util.DateUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class _Helper {

    public static List<DetailCourseModel> generateDetail(Date date, int val, long diffInMinutes, MasterCourseModel master) {
        List<DetailCourseModel> details = new ArrayList<>();
        while (diffInMinutes >= 30) {
            DetailCourseModel detail = new DetailCourseModel();
            detail.setMaster(master);
            detail.setStatus(EDetailCourseStatus.EMPTY.getCode());
            detail.setDescription("empty");
            detail.setFromHour(DateUtil.parse(date, DateUtil.EPattern.HH_mm));
            date = DateUtil.plusMinute(date, 30, DateUtil.EPattern.DD_MM_YYYY_HH_mm_SS);
            detail.setToHour(DateUtil.parse(date, DateUtil.EPattern.HH_mm));
            details.add(detail);
            diffInMinutes -= 30;
        }
        return details;
    }
}
