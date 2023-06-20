package com.blubank.doctorappointment.repository.custom;

import com.blubank.doctorappointment.model.MasterTimeModel;
import org.springframework.stereotype.Repository;

@Repository
public class MasterTimeRepositoryImpl extends BaseJinqRepositoryImpl<MasterTimeModel> implements PatientCustomRepository {
    @Override
    protected Class<MasterTimeModel> entityType() {
        return MasterTimeModel.class;
    }
}
