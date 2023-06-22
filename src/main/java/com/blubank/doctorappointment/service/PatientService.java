package com.blubank.doctorappointment.service;

import com.blubank.doctorappointment.common.EntityMapper;
import com.blubank.doctorappointment.controller.dto.DTODetailCourse;
import com.blubank.doctorappointment.controller.dto.DTOPatient;
import com.blubank.doctorappointment.controller.dto.DTOReserve;
import com.blubank.doctorappointment.model.DetailCourseModel;
import com.blubank.doctorappointment.model.MasterCourseModel;
import com.blubank.doctorappointment.model.PatientModel;
import com.blubank.doctorappointment.model.enums.EDetailCourseStatus;
import com.blubank.doctorappointment.repository.DetailCourseRepository;
import com.blubank.doctorappointment.repository.MasterCourseRepository;
import com.blubank.doctorappointment.repository.PatientRepository;
import com.blubank.doctorappointment.service.excp.ExcpServiceNotFoundCourseException;
import com.blubank.doctorappointment.service.excp.ExcpServiceReserveCourseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    Logger LOG = LoggerFactory.getLogger(PatientService.class);
    MasterCourseRepository masterCourseRepository;
    DetailCourseRepository detailCourseRepository;
    PatientRepository patientRepository;

    @Autowired
    public PatientService(MasterCourseRepository masterCourseRepository,
                          DetailCourseRepository detailCourseRepository,
                          PatientRepository patientRepository) {
        this.masterCourseRepository = masterCourseRepository;
        this.detailCourseRepository = detailCourseRepository;
        this.patientRepository = patientRepository;

    }
    public DTOPatient fetchReserveCourse(String mobil) throws Exception {
        Optional<PatientModel> model = patientRepository.findByMobil(mobil);
        if (model.isEmpty()) return null;
        return EntityMapper.INSTANCE.toDtoPatient(model.get());
    }

    @Transactional
    public String doReserveCourse(DTOReserve dtoReserve) {
        LOG.info("thread id: {}, firstName: {}, lastName: {}, mobil: {}, courseId: {}",
                Thread.currentThread().getId(), dtoReserve.getFirstName(), dtoReserve.getLastName(), dtoReserve.getMobil(), dtoReserve.getCourseId());
        Optional<DetailCourseModel> detailCourseModel = detailCourseRepository.findById(dtoReserve.getCourseId());
        if (detailCourseModel.isEmpty())
            throw new ExcpServiceNotFoundCourseException("دوره مورد نظر یافت نشد، لطفا شناسه دوره را صحیح وارد نمائید.");
        if (detailCourseModel.get().getStatus() != EDetailCourseStatus.EMPTY.getCode())
            throw new ExcpServiceReserveCourseException("وضعیت دوره مورد نظر " + EDetailCourseStatus.getValue(detailCourseModel.get().getStatus()).getPName() + " شده می باشد.");
        LOG.info("course id for reserve: {}", dtoReserve.getCourseId());
        Optional<PatientModel> patientModel = patientRepository.findByMobil(dtoReserve.getMobil());
        if (patientModel.isEmpty()) {
            PatientModel newPatientModel = EntityMapper.INSTANCE.toModel(dtoReserve);
            patientRepository.save(newPatientModel);//new patient
            detailCourseModel.get().setPatient(newPatientModel);
        } else detailCourseModel.get().setPatient(patientModel.get());
        detailCourseModel.get().setStatus(EDetailCourseStatus.RESERVE.getCode());
        Optional<MasterCourseModel> masterCourseModel = masterCourseRepository.findById(detailCourseModel.get().getMaster().getId());
        masterCourseModel.get().setCountReserve(masterCourseModel.get().getCountReserve() + 1);
        masterCourseModel.get().setCountEmpty(masterCourseModel.get().getCountEmpty() - 1);
        LOG.info("change status course to reserve for {} successfully: {}", dtoReserve.getMobil(), dtoReserve.getCourseId());
        return "رزرو نوبت با موفقیت انجام شد.";
    }

}
