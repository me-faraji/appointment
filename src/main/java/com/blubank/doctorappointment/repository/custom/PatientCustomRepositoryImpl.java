package com.blubank.doctorappointment.repository.custom;

import com.blubank.doctorappointment.model.PatientModel;

public class PatientCustomRepositoryImpl extends BaseJinqRepositoryImpl<PatientModel> implements PatientCustomRepository {
    @Override
    protected Class<PatientModel> entityType() {
        return PatientModel.class;
    }
}
