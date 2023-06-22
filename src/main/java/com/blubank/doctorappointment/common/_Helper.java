package com.blubank.doctorappointment.common;

import com.blubank.doctorappointment.model.DetailAppointmentModel;
import com.blubank.doctorappointment.model.MasterAppointmentModel;
import com.blubank.doctorappointment.model.enums.EDetailAppointmentStatus;
import com.blubank.doctorappointment.util.DateUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class _Helper {

    public static List<DetailAppointmentModel> generateDetail(Date date, int val, long diffInMinutes, MasterAppointmentModel master) {
        List<DetailAppointmentModel> details = new ArrayList<>();
        while (diffInMinutes >= 30) {
            DetailAppointmentModel detail = new DetailAppointmentModel();
            detail.setMaster(master);
            detail.setStatus(EDetailAppointmentStatus.EMPTY.getCode());
            detail.setFromHour(DateUtil.parse(date, DateUtil.EPattern.HH_mm));
            date = DateUtil.plusMinute(date, 30, DateUtil.EPattern.DD_MM_YYYY_HH_mm_SS);
            detail.setToHour(DateUtil.parse(date, DateUtil.EPattern.HH_mm));
            details.add(detail);
            diffInMinutes -= 30;
        }
        return details;
    }
}
