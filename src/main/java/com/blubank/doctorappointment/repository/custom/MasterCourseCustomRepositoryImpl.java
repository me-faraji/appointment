package com.blubank.doctorappointment.repository.custom;

import com.blubank.doctorappointment.model.MasterCourseModel;

public class MasterCourseCustomRepositoryImpl extends BaseJinqRepositoryImpl<MasterCourseModel> implements MasterCourseCustomRepository {
    @Override
    protected Class<MasterCourseModel> entityType() {
        return MasterCourseModel.class;
    }
}
