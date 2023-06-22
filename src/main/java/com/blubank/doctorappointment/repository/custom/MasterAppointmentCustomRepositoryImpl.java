package com.blubank.doctorappointment.repository.custom;

import com.blubank.doctorappointment.model.DetailAppointmentModel;
import com.blubank.doctorappointment.model.MasterAppointmentModel;
import com.blubank.doctorappointment.service.DoctorService;
import org.jinq.orm.stream.JinqStream;
import org.jinq.tuples.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class MasterAppointmentCustomRepositoryImpl extends BaseJinqRepositoryImpl<MasterAppointmentModel> implements MasterAppointmentCustomRepository {
    Logger LOG = LoggerFactory.getLogger(DoctorService.class);
    @Override
    protected Class<MasterAppointmentModel> entityType() {
        return MasterAppointmentModel.class;
    }

    @Override
    public List<DetailAppointmentModel> findByDateAndStatusDetail(Date date, int status) throws Exception {
        LOG.info("date: {}, status: {}", date, status);
        return stream()
                .where(m -> m.getDate().equals(date))
                .leftOuterJoin(m -> JinqStream.from(m.getDetail()))
                .where(pair -> pair.getTwo().getStatus() == status)
                .toList()
                .stream().map(Pair::getTwo).collect(Collectors.toList());
    }
}
