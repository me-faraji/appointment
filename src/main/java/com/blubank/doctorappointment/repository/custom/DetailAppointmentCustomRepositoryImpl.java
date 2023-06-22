package com.blubank.doctorappointment.repository.custom;

import com.blubank.doctorappointment.model.DetailAppointmentModel;

public class DetailAppointmentCustomRepositoryImpl extends BaseJinqRepositoryImpl<DetailAppointmentModel> implements DetailAppointmentCustomRepository {
    @Override
    protected Class<DetailAppointmentModel> entityType() {
        return DetailAppointmentModel.class;
    }

}
