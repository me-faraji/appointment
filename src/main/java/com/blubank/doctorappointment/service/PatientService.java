package com.blubank.doctorappointment.service;

import com.blubank.doctorappointment.common.EntityMapper;
import com.blubank.doctorappointment.controller.dto.DTOPatient;
import com.blubank.doctorappointment.controller.dto.DTOReserve;
import com.blubank.doctorappointment.model.DetailAppointmentModel;
import com.blubank.doctorappointment.model.MasterAppointmentModel;
import com.blubank.doctorappointment.model.PatientModel;
import com.blubank.doctorappointment.model.enums.EDetailAppointmentStatus;
import com.blubank.doctorappointment.repository.DetailAppointmentRepository;
import com.blubank.doctorappointment.repository.MasterAppointmentRepository;
import com.blubank.doctorappointment.repository.PatientRepository;
import com.blubank.doctorappointment.service.excp.ExcpServiceNotFoundAppointmentException;
import com.blubank.doctorappointment.service.excp.ExcpServiceReserveAppointmentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PatientService {
    Logger LOG = LoggerFactory.getLogger(PatientService.class);
    MasterAppointmentRepository masterAppointmentRepository;
    DetailAppointmentRepository detailAppointmentRepository;
    PatientRepository patientRepository;

    @Autowired
    public PatientService(MasterAppointmentRepository masterAppointmentRepository,
                          DetailAppointmentRepository detailAppointmentRepository,
                          PatientRepository patientRepository) {
        this.masterAppointmentRepository = masterAppointmentRepository;
        this.detailAppointmentRepository = detailAppointmentRepository;
        this.patientRepository = patientRepository;

    }
    public DTOPatient fetchReserveAppointment(String mobil) throws Exception {
        Optional<PatientModel> model = patientRepository.findByMobil(mobil);
        if (model.isEmpty()) return null;
        return EntityMapper.INSTANCE.toDtoPatient(model.get());
    }

    @Transactional
    public String doReserveAppointment(DTOReserve dtoReserve) {
        LOG.info("thread id: {}, firstName: {}, lastName: {}, mobil: {}, AppointmentId: {}",
                Thread.currentThread().getId(), dtoReserve.getFirstName(), dtoReserve.getLastName(), dtoReserve.getMobil(), dtoReserve.getAppointmentId());
        Optional<DetailAppointmentModel> detailAppointmentModel = detailAppointmentRepository.findById(dtoReserve.getAppointmentId());
        if (detailAppointmentModel.isEmpty())
            throw new ExcpServiceNotFoundAppointmentException("دوره مورد نظر یافت نشد، لطفا شناسه دوره را صحیح وارد نمائید.");
        if (detailAppointmentModel.get().getStatus() != EDetailAppointmentStatus.EMPTY.getCode())
            throw new ExcpServiceReserveAppointmentException("وضعیت دوره مورد نظر " + EDetailAppointmentStatus.getValue(detailAppointmentModel.get().getStatus()).getPName() + " شده می باشد.");
        LOG.info("Appointment id for reserve: {}", dtoReserve.getAppointmentId());
        Optional<PatientModel> patientModel = patientRepository.findByMobil(dtoReserve.getMobil());
        if (patientModel.isEmpty()) {
            PatientModel newPatientModel = EntityMapper.INSTANCE.toModel(dtoReserve);
            patientRepository.save(newPatientModel);//new patient
            detailAppointmentModel.get().setPatient(newPatientModel);
        } else detailAppointmentModel.get().setPatient(patientModel.get());
        detailAppointmentModel.get().setStatus(EDetailAppointmentStatus.RESERVE.getCode());
        Optional<MasterAppointmentModel> masterAppointmentModel = masterAppointmentRepository.findById(detailAppointmentModel.get().getMaster().getId());
        masterAppointmentModel.get().setCountReserve(masterAppointmentModel.get().getCountReserve() + 1);
        masterAppointmentModel.get().setCountEmpty(masterAppointmentModel.get().getCountEmpty() - 1);
        LOG.info("change status Appointment to reserve for {} successfully: {}", dtoReserve.getMobil(), dtoReserve.getAppointmentId());
        return "رزرو نوبت با موفقیت انجام شد.";
    }

}
