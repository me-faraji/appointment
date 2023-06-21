package com.blubank.doctorappointment.repository.custom;

import com.blubank.doctorappointment.model.DetailCourseModel;
import com.blubank.doctorappointment.model.MasterCourseModel;
import com.blubank.doctorappointment.service.DoctorService;
import org.jinq.orm.stream.JinqStream;
import org.jinq.tuples.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class MasterCourseCustomRepositoryImpl extends BaseJinqRepositoryImpl<MasterCourseModel> implements MasterCourseCustomRepository {
    Logger LOG = LoggerFactory.getLogger(DoctorService.class);
    @Override
    protected Class<MasterCourseModel> entityType() {
        return MasterCourseModel.class;
    }

    @Override
    public List<DetailCourseModel> findByDateAndStatusCourse(Date date, int status) throws Exception {
        LOG.info("date: {}, status: {}", date, status);
        return stream()
                .where(m -> m.getDate().equals(date))
                .leftOuterJoin(m -> JinqStream.from(m.getDetail()))
                .where(pair -> pair.getTwo().getStatus() == status)
                .toList()
                .stream().map(Pair::getTwo).collect(Collectors.toList());
    }
}
