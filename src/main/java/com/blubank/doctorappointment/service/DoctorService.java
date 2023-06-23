package com.blubank.doctorappointment.service;

import com.blubank.doctorappointment.common._Helper;
import com.blubank.doctorappointment.common.EntityMapper;
import com.blubank.doctorappointment.controller.dto.DTODetailAppointment;
import com.blubank.doctorappointment.controller.dto.DTOMasterAppointment;
import com.blubank.doctorappointment.model.DetailAppointmentModel;
import com.blubank.doctorappointment.model.MasterAppointmentModel;
import com.blubank.doctorappointment.model.enums.EDetailAppointmentStatus;
import com.blubank.doctorappointment.repository.DetailAppointmentRepository;
import com.blubank.doctorappointment.repository.MasterAppointmentRepository;
import com.blubank.doctorappointment.service.excp.ExcpServiceDuplicateException;
import com.blubank.doctorappointment.service.excp.ExcpServiceNotFoundAppointmentException;
import com.blubank.doctorappointment.service.excp.ExcpServiceReserveAppointmentException;
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
    MasterAppointmentRepository masterAppointmentRepository;
    DetailAppointmentRepository detailAppointmentRepository;

    @Autowired
    public DoctorService(MasterAppointmentRepository masterAppointmentRepository,
                         DetailAppointmentRepository detailAppointmentRepository) {
        this.masterAppointmentRepository = masterAppointmentRepository;
        this.detailAppointmentRepository = detailAppointmentRepository;
    }

    @Transactional
    public String doSaveAppointment(Date dateTime, String strFromDate, long diffInMinutes) throws Exception {
        Date date = DateUtil.parse(strFromDate, DateUtil.EPattern.DD_MM_YYYY);
        if (masterAppointmentRepository.findByDate(date).isPresent())
            throw new ExcpServiceDuplicateException("تاریخ مورد نظر قبلا زمانبندی شده است.");
        LOG.info("from: {}, diffInMinutes: {}", date, diffInMinutes);
        MasterAppointmentModel master = new MasterAppointmentModel();
        master.setDate(date);
        master.setCountDelete(0);
        master.setCountReserve(0);
        master.setCountDischarge(0);
        masterAppointmentRepository.save(master);
        LOG.info("persist master: {}", master.getId());
        List<DetailAppointmentModel> details = _Helper.generateDetail(dateTime, 30, diffInMinutes, master);
        master.setCapacity(details.size());
        master.setCountEmpty(details.size());
        detailAppointmentRepository.saveAll(details);
        LOG.info("persist detail: {}", details.size());
        return "وقت ملاقات تاریخ مورد نظر با موفقیت تنظیم شد.";
    }
    public DTOMasterAppointment fetchDetail(Date date) throws Exception {
        Optional<MasterAppointmentModel> master = masterAppointmentRepository.findByDate(date);
        if (master.isEmpty()) return null;
        return EntityMapper.INSTANCE.toDto(master.get());
    }
    public List<DTODetailAppointment> fetchDetail(Date date, EDetailAppointmentStatus eDetailAppointmentStatus) throws Exception {
        List<DetailAppointmentModel> details = masterAppointmentRepository.findByDateAndStatusDetail(date, eDetailAppointmentStatus.getCode());
        if (details.isEmpty()) return null;
        return EntityMapper.INSTANCE.toDto(details);
    }
    @Transactional
    public String doDeleteAppointmentById(long id) {
        Optional<DetailAppointmentModel> detailAppointmentModel = detailAppointmentRepository.findById(id);
        if (detailAppointmentModel.isEmpty())
            throw new ExcpServiceNotFoundAppointmentException("دوره مورد نظر یافت نشد، لطفا شناسه دوره را صحیح وارد نمائید.");
        if (detailAppointmentModel.get().getStatus() != EDetailAppointmentStatus.EMPTY.getCode())
            throw new ExcpServiceReserveAppointmentException("وضعیت دوره مورد نظر " + EDetailAppointmentStatus.getValue(detailAppointmentModel.get().getStatus()).getPName() + " شده می باشد.");
        LOG.info("Appointment id for deleted: {}", id);
        detailAppointmentModel.get().setStatus(EDetailAppointmentStatus.DELETE.getCode());
        detailAppointmentRepository.save(detailAppointmentModel.get());
        Optional<MasterAppointmentModel> masterAppointmentModel = masterAppointmentRepository.findById(detailAppointmentModel.get().getMaster().getId());
        masterAppointmentModel.get().setCountDelete(masterAppointmentModel.get().getCountDelete() + 1);
        masterAppointmentModel.get().setCountEmpty(masterAppointmentModel.get().getCountEmpty() - 1);
        LOG.info("change status Appointment to delete successfully: {}", id);
        return "وقت ملاقات مورد نظر با موفقیت حذف شد.";
    }
}
