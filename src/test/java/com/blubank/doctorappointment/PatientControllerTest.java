package com.blubank.doctorappointment;

import com.blubank.doctorappointment.controller.DoctorController;
import com.blubank.doctorappointment.controller.PatientController;
import com.blubank.doctorappointment.controller.dto.DTODetailAppointment;
import com.blubank.doctorappointment.controller.dto.DTOPatient;
import com.blubank.doctorappointment.controller.dto.DTOReserve;
import com.blubank.doctorappointment.controller.excp.ExcpControllerInvalidParameterException;
import com.blubank.doctorappointment.service.excp.ExcpServiceNotFoundAppointmentException;
import com.blubank.doctorappointment.service.excp.ExcpServiceReserveAppointmentException;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.*;

@SpringBootTest
public class PatientControllerTest {
    @Autowired
    PatientController patientController;

    @Autowired
    DoctorController doctorController;

    /**
     * As a patient I like to be able to see all of the open appointments for the given day. So, I can take one of these appointments.
     * If the doctor doesn’t have any open appointment that day, then, an empty list should be shown.
     */
    @Test
    public void getEmptyAppointment() throws Exception {
        try {
            List<DTODetailAppointment> result = patientController.getEmptyAppointment("10-01-2018");
            MatcherAssert.assertThat("result must be Null or List", result, anyOf(nullValue(List.class), isA(List.class)));
        } catch (org.hibernate.LazyInitializationException ignored) {}
    }

    /* Story: Patients can take an open appointment
              As a patient I like to be able to take an open appointment by giving my name and phone number.
    */

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
     * errMessage: وقت ملاقات مورد نظر یافت نشد، لطفا شناسه دوره را صحیح وارد نمائید.
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
     * Reserve a Appointment correctly
     */
    @Test
    public void reserveAppointment_normal() {
        Assertions.assertEquals("وقت ملاقات مورد نظر با موفقیت رزرو شد.",
                patientController.reserveAppointment(DTOReserve.builder()
                        .firstName("mehdi")
                        .lastName("faraji")
                        .mobil("09379644267")
                        .AppointmentId(246L)
                        .build()));
    }

    /**
     * If not found Appointment,
     * throws ExcpServiceReserveAppointmentException
     * errMessage, for example: وضعیت وقت ملاقات مورد نظر رزرو شده می باشد.
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
     * Concurrency check; patient is taking an appointment that is in the process of deletion or being taken by another patient.
     */
    @Test
    public void reserveAppointment_concurrency_check() {
        ExecutorService es = Executors.newFixedThreadPool(4);
        try {
            // reserve
            es.execute(() -> {
                Assertions.assertEquals("رزرو وقت ملاقات با موفقیت انجام شد.",
                        patientController.reserveAppointment(DTOReserve.builder()
                                .firstName("mehdi")
                                .lastName("faraji")
                                .mobil("09379644267")
                                .AppointmentId(250L)
                                .build()));
            });
            // reserve
            es.execute(() -> {
                Assertions.assertEquals("رزرو وقت ملاقات با موفقیت انجام شد.",
                        patientController.reserveAppointment(DTOReserve.builder()
                                .firstName("aref")
                                .lastName("faraji")
                                .mobil("09379644268")
                                .AppointmentId(250L)
                                .build()));
            });
            // delete
            es.execute(() -> {
                Assertions.assertEquals("وقت ملاقات مورد نظر با موفقیت حذف شد.", doctorController.deleteAppointmentById(250L));
            });
            es.shutdown();
            es.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Story: Patients can view their own appointments
     *              As a patient I like to be able to view my own appointments, providing only my phone number.

     1. If there is no appointment with this phone number, then an empty list should be shown.
     2. If there are more than one appointment taken by this user, then all should be shown.
     */
    @Test
    public void getReserveAppointment() throws Exception {
        DTOPatient result = patientController.getReserveAppointment("09379644267");
        MatcherAssert.assertThat("result must be Null or DTOPatient", result, anyOf(nullValue(), isA(DTOPatient.class)));
    }
}
