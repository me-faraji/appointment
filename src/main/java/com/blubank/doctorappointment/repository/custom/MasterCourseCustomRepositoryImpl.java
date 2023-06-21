package com.blubank.doctorappointment.repository.custom;

import com.blubank.doctorappointment.model.MasterCourseModel;

import java.util.Date;
import java.util.Optional;

public class MasterCourseCustomRepositoryImpl extends BaseJinqRepositoryImpl<MasterCourseModel> implements MasterCourseCustomRepository {
    @Override
    protected Class<MasterCourseModel> entityType() {
        return MasterCourseModel.class;
    }

    @Override
    public Optional<MasterCourseModel> fetchByFromDate(Date fromDate) throws Exception {
        return stream().where(d -> d.getFromDate().before(fromDate) || d.getFromDate().equals(fromDate)).findFirst();
    }
}
