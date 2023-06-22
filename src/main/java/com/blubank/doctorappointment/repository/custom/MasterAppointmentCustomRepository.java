package com.blubank.doctorappointment.repository.custom;

import com.blubank.doctorappointment.model.DetailAppointmentModel;

import java.util.Date;
import java.util.List;

public interface MasterAppointmentCustomRepository {
    List<DetailAppointmentModel> findByDateAndStatusDetail(Date date, int status) throws Exception;
}
