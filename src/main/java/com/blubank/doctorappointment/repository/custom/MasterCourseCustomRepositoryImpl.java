package com.blubank.doctorappointment.repository.custom;

import com.blubank.doctorappointment.model.MasterCourseModel;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MasterCourseCustomRepositoryImpl extends BaseJinqRepositoryImpl<MasterCourseModel> implements MasterCourseCustomRepository {
    @Override
    protected Class<MasterCourseModel> entityType() {
        return MasterCourseModel.class;
    }

    @Override
    public Optional<MasterCourseModel> findByDate(Date fromDate) throws Exception {
        return stream().where(d -> d.getDate().before(fromDate) || d.getDate().equals(fromDate)).findFirst();
    }

}
