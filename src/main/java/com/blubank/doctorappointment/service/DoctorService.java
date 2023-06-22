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
    public String doSaveCourse(Date dateTime, String strFromDate, long diffInMinutes) throws Exception {
        Date date = DateUtil.parse(strFromDate, DateUtil.EPattern.DD_MM_YYYY);
        if (masterCourseRepository.findByDate(date).isPresent())
            throw new ExcpServiceDuplicateException("تاریخ مورد نظر قبلا زمانبندی شده است.");
        LOG.info("from: {}, diffInMinutes: {}", date, diffInMinutes);
        MasterCourseModel master = new MasterCourseModel();
        master.setDate(date);
        master.setCountDelete(0);
        master.setCountReserve(0);
        master.setCountDischarge(0);
        masterCourseRepository.save(master);
        LOG.info("persist master: {}", master.getId());
        List<DetailCourseModel> details = _Helper.generateDetail(dateTime, 30, diffInMinutes, master);
        master.setCapacity(details.size());
        master.setCountEmpty(details.size());
        detailCourseRepository.saveAll(details);
        LOG.info("persist detail: {}", details.size());
        return "تعریف دوره با موفقیت انجام شد.";
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
        Optional<DetailCourseModel> detailCourseModel = detailCourseRepository.findById(id);
        if (detailCourseModel.isEmpty())
            throw new ExcpServiceNotFoundCourseException("دوره مورد نظر یافت نشد، لطفا شناسه دوره را صحیح وارد نمائید.");
        if (detailCourseModel.get().getStatus() != EDetailCourseStatus.EMPTY.getCode())
            throw new ExcpServiceReserveCourseException("وضعیت دوره مورد نظر " + EDetailCourseStatus.getValue(detailCourseModel.get().getStatus()).getPName() + " شده می باشد.");
        LOG.info("course id for deleted: {}", id);
        detailCourseModel.get().setStatus(EDetailCourseStatus.DELETE.getCode());
        detailCourseRepository.save(detailCourseModel.get());
        Optional<MasterCourseModel> masterCourseModel = masterCourseRepository.findById(detailCourseModel.get().getMaster().getId());
        masterCourseModel.get().setCountDelete(masterCourseModel.get().getCountDelete() + 1);
        masterCourseModel.get().setCountEmpty(masterCourseModel.get().getCountEmpty() - 1);
        LOG.info("change status course to delete successfully: {}", id);
        return "دوره مورد نظر با موفقیت حذف شد.";
    }
}
