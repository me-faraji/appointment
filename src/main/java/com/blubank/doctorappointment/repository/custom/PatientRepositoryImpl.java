package com.blubank.doctorappointment.repository.custom;

import com.blubank.doctorappointment.model.PatientModel;
import org.springframework.stereotype.Repository;

@Repository
public class PatientRepositoryImpl extends BaseJinqRepositoryImpl<PatientModel> implements PatientCustomRepository {
    @Override
    protected Class<PatientModel> entityType() {
        return PatientModel.class;
    }
}
