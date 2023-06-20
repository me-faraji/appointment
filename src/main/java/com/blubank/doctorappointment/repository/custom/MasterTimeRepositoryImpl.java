package com.blubank.doctorappointment.repository.custom;

import com.blubank.doctorappointment.model.MasterCourseModel;
import org.springframework.stereotype.Repository;

@Repository
public class MasterTimeRepositoryImpl extends BaseJinqRepositoryImpl<MasterCourseModel> implements PatientCustomRepository {
    @Override
    protected Class<MasterCourseModel> entityType() {
        return MasterCourseModel.class;
    }
}
