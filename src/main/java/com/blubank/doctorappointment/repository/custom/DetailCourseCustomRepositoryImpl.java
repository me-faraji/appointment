package com.blubank.doctorappointment.repository.custom;

import com.blubank.doctorappointment.model.DetailCourseModel;
import com.blubank.doctorappointment.model.MasterCourseModel;
import org.jinq.orm.stream.JinqStream;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class DetailCourseCustomRepositoryImpl extends BaseJinqRepositoryImpl<DetailCourseModel> implements DetailCourseCustomRepository {
    @Override
    protected Class<DetailCourseModel> entityType() {
        return DetailCourseModel.class;
    }

}
