package com.blubank.doctorappointment.service;

import com.blubank.doctorappointment.common._Helper;
import com.blubank.doctorappointment.controller.DoctorController;
import com.blubank.doctorappointment.controller.dto.DTODetailCourse;
import com.blubank.doctorappointment.model.DetailCourseModel;
import com.blubank.doctorappointment.model.MasterCourseModel;
import com.blubank.doctorappointment.repository.DetailCourseRepository;
import com.blubank.doctorappointment.repository.MasterCourseRepository;
import com.blubank.doctorappointment.service.excp.ExcpServiceDuplicateException;
import com.blubank.doctorappointment.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

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
    public MasterCourseModel doSaveCourse(Date from, Date to, long diffInMinutes) throws Exception {
        if (masterCourseRepository.fetchByFromDate(from).isPresent())
            throw new ExcpServiceDuplicateException("تاریخ مورد نظر قبلا زمانبندی شده است.");
        LOG.info("from: {}, to: {}, diffInMinutes: {}", from, to, diffInMinutes);
        MasterCourseModel master = new MasterCourseModel();
        master.setFromDate(from);
        master.setToDate(to);
        master.setStatus(1);
        master.setDescription("init");
        masterCourseRepository.save(master);
        LOG.info("persist master: {}", master.getId());
        List<DetailCourseModel> details = _Helper.generateDetail(from, 30, diffInMinutes, null);
//        _Helper.generateDetail(from, 30, diffInMinutes, null).forEach(f -> {
//            System.out.println(f.getFromHour() + " ---> " + f.getToHour());
//        });
        detailCourseRepository.saveAll(details);
        LOG.info("persist detail: {}", details.size());
        return master;
    }
    public List<DTODetailCourse> fetchDetailCourseByDate() {
        return null;
    }
    public DTODetailCourse doDeleteDetailCourseById() {
        return null;
    }
}
