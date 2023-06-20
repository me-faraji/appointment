package com.blubank.doctorappointment.repository.custom;

import com.blubank.doctorappointment.model.DetailTimeModel;
import org.springframework.stereotype.Repository;

@Repository
public class DetailTimeRepositoryImpl extends BaseJinqRepositoryImpl<DetailTimeModel> implements PatientCustomRepository {
    @Override
    protected Class<DetailTimeModel> entityType() {
        return DetailTimeModel.class;
    }
}
