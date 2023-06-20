package com.blubank.doctorappointment.repository.custom;

import com.blubank.doctorappointment.model.DetailCourseModel;
import org.springframework.stereotype.Repository;

@Repository
public class DetailCourseRepositoryImpl extends BaseJinqRepositoryImpl<DetailCourseModel> implements PatientCustomRepository {
    @Override
    protected Class<DetailCourseModel> entityType() {
        return DetailCourseModel.class;
    }
}
