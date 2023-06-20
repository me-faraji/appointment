package com.blubank.doctorappointment.repository.custom;

import com.blubank.doctorappointment.model.DetailCourseModel;

public class DetailCourseCustomRepositoryImpl extends BaseJinqRepositoryImpl<DetailCourseModel> implements DetailCourseCustomRepository {
    @Override
    protected Class<DetailCourseModel> entityType() {
        return DetailCourseModel.class;
    }
}
