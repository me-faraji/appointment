package com.blubank.doctorappointment.repository.custom;

import com.blubank.doctorappointment.model.DetailCourseModel;
import com.blubank.doctorappointment.model.MasterCourseModel;

import java.util.List;
import java.util.stream.Collectors;

public class DetailCourseCustomRepositoryImpl extends BaseJinqRepositoryImpl<DetailCourseModel> implements DetailCourseCustomRepository {
    @Override
    protected Class<DetailCourseModel> entityType() {
        return DetailCourseModel.class;
    }

    @Override
    public List<DetailCourseModel> findByMaster(MasterCourseModel masterCourseModel) throws Exception {
        return stream().where(m -> m.getMaster().equals(masterCourseModel)).collect(Collectors.toList());
    }
}
