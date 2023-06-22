package com.blubank.doctorappointment;

import com.blubank.doctorappointment.controller.PatientController;
import com.blubank.doctorappointment.controller.dto.DTOReserve;
import com.blubank.doctorappointment.controller.excp.ExcpControllerInvalidParameterException;
import com.blubank.doctorappointment.service.excp.ExcpServiceNotFoundAppointmentException;
import com.blubank.doctorappointment.service.excp.ExcpServiceReserveAppointmentException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class PatientControllerTest {
    @Autowired
    PatientController patientController;

    // -------------------------------------------------------------- reserve Appointment -----------------------------------
    /**
     * If invalid parameter,
     * throws ExcpControllerInvalidParameterException
     * errMessage: لطفا پارامترها را صحیح وارد نمائید.
     * desc: in this scenario Appointment ID is not entered
     */
    @Test
    public void creareAppointment_If_entered_invalid_parameter() {
        Assertions.assertThrows(ExcpControllerInvalidParameterException.class,
                () -> {patientController.reserveAppointment(DTOReserve.builder()
                        .firstName("mehdi")
                        .lastName("faraji")
                        .mobil("09379644267")
                        // Appointment ID is not entered
                        .build());});
    }

    /**
     * If not found Appointment,
     * throws ExcpServiceNotFoundAppointmentException
     * errMessage: دوره مورد نظر یافت نشد، لطفا شناسه دوره را صحیح وارد نمائید.
     */
    @Test
    public void creareAppointment_If_not_found_appointment() {
        Assertions.assertThrows(ExcpServiceNotFoundAppointmentException.class,
                () -> {patientController.reserveAppointment(DTOReserve.builder()
                        .firstName("mehdi")
                        .lastName("faraji")
                        .mobil("09379644267")
                        .AppointmentId(37L)
                        .build());});
    }

    /**
     * TestCase: Reserve a Appointment correctly
     */
    @Test
    public void reserveAppointment_normal() {
        Assertions.assertEquals("رزرو نوبت با موفقیت انجام شد.",
                patientController.reserveAppointment(DTOReserve.builder()
                        .firstName("mehdi")
                        .lastName("faraji")
                        .mobil("09379644267")
                        .AppointmentId(236L)
                        .build()));
    }

    /**
     * If not found Appointment,
     * throws ExcpServiceReserveAppointmentException
     * errMessage, for example: وضعیت دوره مورد نظر رزرو شده می باشد.
     */
    @Test
    public void creareAppointment_If_status_appointment_not_empty() {
        Assertions.assertThrows(ExcpServiceReserveAppointmentException.class,
                () -> {patientController.reserveAppointment(DTOReserve.builder()
                        .firstName("mehdi")
                        .lastName("faraji")
                        .mobil("09379644267")
                        .AppointmentId(233L)
                        .build());});
    }
    /**
     * TestCase: Reserve a Appointment by tow patient
     */
    @Test
    public void reserveAppointment_concurrency_check() {
        ExecutorService es = Executors.newFixedThreadPool(3);
        try {
            es.execute(() -> {
                Assertions.assertEquals("رزرو نوبت با موفقیت انجام شد.",
                        patientController.reserveAppointment(DTOReserve.builder()
                                .firstName("sharif")
                                .lastName("faraji")
                                .mobil("09379644269")
                                .AppointmentId(236L)
                                .build()));
            });
            es.execute(() -> {
                Assertions.assertEquals("رزرو نوبت با موفقیت انجام شد.",
                        patientController.reserveAppointment(DTOReserve.builder()
                                .firstName("aref")
                                .lastName("faraji")
                                .mobil("09379644268")
                                .AppointmentId(236L)
                                .build()));
            });
            es.shutdown();
            es.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
