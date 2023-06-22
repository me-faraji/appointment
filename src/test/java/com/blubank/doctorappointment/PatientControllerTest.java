package com.blubank.doctorappointment;

import com.blubank.doctorappointment.controller.PatientController;
import com.blubank.doctorappointment.controller.dto.DTOReserve;
import com.blubank.doctorappointment.controller.excp.ExcpControllerInvalidParameterException;
import com.blubank.doctorappointment.service.excp.ExcpServiceNotFoundCourseException;
import com.blubank.doctorappointment.service.excp.ExcpServiceReserveCourseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.*;

@SpringBootTest
public class PatientControllerTest {
    @Autowired
    PatientController patientController;

    // -------------------------------------------------------------- reserve course -----------------------------------
    /**
     * If invalid parameter,
     * throws ExcpControllerInvalidParameterException
     * errMessage: لطفا پارامترها را صحیح وارد نمائید.
     * desc: in this scenario Course ID is not entered
     */
    @Test
    public void creareCourse_If_entered_invalid_parameter() {
        Assertions.assertThrows(ExcpControllerInvalidParameterException.class,
                () -> {patientController.reserveCourse(DTOReserve.builder()
                        .firstName("mehdi")
                        .lastName("faraji")
                        .mobil("09379644267")
                        // Course ID is not entered
                        .build());});
    }

    /**
     * If not found Course,
     * throws ExcpServiceNotFoundCourseException
     * errMessage: دوره مورد نظر یافت نشد، لطفا شناسه دوره را صحیح وارد نمائید.
     */
    @Test
    public void creareCourse_If_not_found_course() {
        Assertions.assertThrows(ExcpServiceNotFoundCourseException.class,
                () -> {patientController.reserveCourse(DTOReserve.builder()
                        .firstName("mehdi")
                        .lastName("faraji")
                        .mobil("09379644267")
                        .courseId(37L)
                        .build());});
    }

    /**
     * TestCase: Reserve a course correctly
     */
    @Test
    public void reserveCourse_normal() {
        Assertions.assertEquals("رزرو نوبت با موفقیت انجام شد.",
                patientController.reserveCourse(DTOReserve.builder()
                        .firstName("mehdi")
                        .lastName("faraji")
                        .mobil("09379644267")
                        .courseId(236L)
                        .build()));
    }

    /**
     * If not found Course,
     * throws ExcpServiceReserveCourseException
     * errMessage, for example: وضعیت دوره مورد نظر رزرو شده می باشد.
     */
    @Test
    public void creareCourse_If_status_course_not_empty() {
        Assertions.assertThrows(ExcpServiceReserveCourseException.class,
                () -> {patientController.reserveCourse(DTOReserve.builder()
                        .firstName("mehdi")
                        .lastName("faraji")
                        .mobil("09379644267")
                        .courseId(233L)
                        .build());});
    }
    /**
     * TestCase: Reserve a course by tow patient
     */
    @Test
    public void reserveCourse_concurrency_check() {
        ExecutorService es = Executors.newFixedThreadPool(3);
        try {
            es.execute(() -> {
                Assertions.assertEquals("رزرو نوبت با موفقیت انجام شد.",
                        patientController.reserveCourse(DTOReserve.builder()
                                .firstName("sharif")
                                .lastName("faraji")
                                .mobil("09379644269")
                                .courseId(236L)
                                .build()));
            });
            es.execute(() -> {
                Assertions.assertEquals("رزرو نوبت با موفقیت انجام شد.",
                        patientController.reserveCourse(DTOReserve.builder()
                                .firstName("aref")
                                .lastName("faraji")
                                .mobil("09379644268")
                                .courseId(236L)
                                .build()));
            });
            es.shutdown();
            es.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
