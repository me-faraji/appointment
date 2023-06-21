package com.blubank.doctorappointment.service;

import com.blubank.doctorappointment.common._Helper;
import com.blubank.doctorappointment.common.EntityMapper;
import com.blubank.doctorappointment.controller.dto.DTODetailCourse;
import com.blubank.doctorappointment.controller.dto.DTOMasterCourse;
import com.blubank.doctorappointment.model.DetailCourseModel;
import com.blubank.doctorappointment.model.MasterCourseModel;
import com.blubank.doctorappointment.model.enums.EDetailCourseStatus;
import com.blubank.doctorappointment.repository.DetailCourseRepository;
import com.blubank.doctorappointment.repository.MasterCourseRepository;
import com.blubank.doctorappointment.service.excp.ExcpServiceDuplicateException;
import com.blubank.doctorappointment.service.excp.ExcpServiceNotFoundCourseException;
import com.blubank.doctorappointment.service.excp.ExcpServiceReserveCourseException;
import com.blubank.doctorappointment.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {
    Logger LOG = LoggerFactory.getLogger(DoctorService.class);
    MasterCourseRepository masterCourseRepository;
    DetailCourseRepository detailCourseRepository;

    @Autowired
    public DoctorService(MasterCourseRepository masterCourseRepository,
                         DetailCourseRepository detailCourseRepository) {
        this.masterCourseRepository = masterCourseRepository;
        this.detailCourseRepository = detailCourseRepository;
    }

    @Transactional
    public MasterCourseModel doSaveCourse(Date from, String strFromDate, long diffInMinutes) throws Exception {
        if (masterCourseRepository.findByDate(from).isPresent())
            throw new ExcpServiceDuplicateException("تاریخ مورد نظر قبلا زمانبندی شده است.");
        LOG.info("from: {}, diffInMinutes: {}", from, diffInMinutes);
        MasterCourseModel master = new MasterCourseModel();
        master.setDate(DateUtil.parse(strFromDate, DateUtil.EPattern.DD_MM_YYYY));
        master.setCountDelete(0);
        master.setCountReserve(0);
        master.setCountDischarge(0);
        masterCourseRepository.save(master);
        LOG.info("persist master: {}", master.getId());
        List<DetailCourseModel> details = _Helper.generateDetail(from, 30, diffInMinutes, master);
        master.setCapacity(details.size());
        master.setCountEmpty(details.size());
        detailCourseRepository.saveAll(details);
        LOG.info("persist detail: {}", details.size());
        return master;
    }
    public DTOMasterCourse fetchCourse(Date date) throws Exception {
        Optional<MasterCourseModel> master = masterCourseRepository.findByDate(date);
        if (master.isEmpty()) return null;
        return EntityMapper.INSTANCE.toDto(master.get());
    }
    public List<DTODetailCourse> fetchCourse(Date date, EDetailCourseStatus eDetailCourseStatus) throws Exception {
        List<DetailCourseModel> details = masterCourseRepository.findByDateAndStatusCourse(date, eDetailCourseStatus.getCode());
        if (details.isEmpty()) return null;
        return EntityMapper.INSTANCE.toDto(details);
    }
    @Transactional
    public String doDeleteCourseById(long id) {
        Optional<DetailCourseModel> model = detailCourseRepository.findById(id);
        if (model.isEmpty())
            throw new ExcpServiceNotFoundCourseException("دوره مورد نظر یافت نشد، لطفا شناسه دوره را صحیح وارد نمائید.");
        if (model.get().getStatus() != EDetailCourseStatus.EMPTY.getCode())
            throw new ExcpServiceReserveCourseException("وضعیت دوره مورد نظر " + EDetailCourseStatus.getValue(model.get().getStatus()).getPName() + " شده می باشد.");
        LOG.info("course id for deleted: {}", id);
        model.get().setStatus(EDetailCourseStatus.DELETE.getCode());
        detailCourseRepository.save(model.get());
        LOG.info("change status course to delete successfully: {}", id);
        return "دوره مورد نظر با موفقیت حذف شد.";
    }
}
