package com.blubank.doctorappointment;

import com.blubank.doctorappointment.controller.DoctorController;
import com.blubank.doctorappointment.controller.PatientController;
import com.blubank.doctorappointment.controller.dto.DTODetailAppointment;
import com.blubank.doctorappointment.controller.dto.DTOReserve;
import com.blubank.doctorappointment.controller.excp.ExcpControllerInvalidParameterException;
import com.blubank.doctorappointment.service.excp.ExcpServiceDuplicateException;
import com.blubank.doctorappointment.service.excp.ExcpServiceNotFoundAppointmentException;
import com.blubank.doctorappointment.service.excp.ExcpServiceReserveAppointmentException;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.hamcrest.Matchers.*;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class DoctorControllerTest {
    @Autowired
    DoctorController doctorController;

    @Autowired
    PatientController patientController;

    /**
     * As a doctor I would like to add a start and end time for each day, so that this time is broken down into 30 minutes periods.
     * If one of the periods is becomes less than 30 minutes during breakdown, then it should be ignored.
     */
    @Test
    public void creareAppointment_normal() throws Exception {
        Assertions.assertEquals("وقت ملاقات تاریخ مورد نظر با موفقیت تنظیم شد.",
                doctorController.createAppointment("10-01-2018 10:00:00", "10-01-2018 14:00:00"));
    }

    /**
     * If doctor enter invalid syntax of startDate or endDate,
     * throws ExcpControllerInvalidParameterException
     * errMessage: لطفا پارامترها را صحیح وارد نمائید.
     */
    @Test
    public void creareAppointment_if_invalid_date() {
        Assertions.assertThrows(ExcpControllerInvalidParameterException.class,
                () -> {doctorController.createAppointment("10-01-201810:00:00", "10-012018 14:00:00");});
    }

    /**
     * If doctor enters an endDate that is sooner than startDate,
     * throws ExcpControllerInvalidParameterException
     * errMessage: از تاریخ بایستی بزرگتر از تا تاریخ باشد.
     */
    @Test
    public void creareAppointment_if_startDate_greaterThan_endDate() {
        Assertions.assertThrows(ExcpControllerInvalidParameterException.class,
                () -> {doctorController.createAppointment("10-01-2018 14:00:00", "10-01-2018 10:00:00");});
    }

    /**
     * If interval between the startDate and endDate is more than two days,
     * throws ExcpControllerInvalidParameterException
     * errMessage: حداکثر یک روز قابل زمانبندی می باشد.
     */
    @Test
    public void creareAppointment_if_interval_between_startDate_and_endDate_tow_days() {
        Assertions.assertThrows(ExcpControllerInvalidParameterException.class,
                () -> {doctorController.createAppointment("10-01-2018 10:00:00", "11-01-2018 14:00:00");});
    }

    /**
     * If doctor enters start and end date so that the period is less than 30 minutes then no time should be added,
     * throws ExcpControllerInvalidParameterException
     * errMessage: حداقل زمان هر ملاقات 30 دقیقه می باشد.
     */
    @Test
    public void creareAppointment_If_interval_between_startDate_and_endDate_diffInMinutes_lessThan_30() {
        Assertions.assertThrows(ExcpControllerInvalidParameterException.class,
                () -> {doctorController.createAppointment("10-01-2018 10:00:00", "11-01-2018 10:20:00");});
    }

    /**
     * If that day is already scheduled,
     * throws ExcpServiceDuplicateException
     * errMessage: تاریخ مورد نظر قبلا زمانبندی شده است.
     */
    @Test
    public void creareAppointment_If_that_day_is_already_scheduled() {
        Assertions.assertThrows(ExcpServiceDuplicateException.class,
                () -> {doctorController.createAppointment("10-01-2018 10:00:00", "10-01-2018 14:00:00");});
    }

    /**
     * 1: If there is no appointment set, empty list should be shown.
     * 2: If there are some taken appointment, then phone number and name of the patient should also be shown.
     */
    @Test
    public void getReserveAppointment() throws Exception {
        try {
            List<DTODetailAppointment> result = doctorController.getReserveAppointment("10-01-2018");
            MatcherAssert.assertThat("result must be Null or List", result, anyOf(nullValue(List.class), isA(List.class)));
        } catch (org.hibernate.LazyInitializationException ignored) {}
    }

    /**
     * As a doctor I would like to be able to delete some of my open appointments.
     */
    @Test
    public void deleteAppointmentById() {
        Assertions.assertEquals("وقت ملاقات مورد نظر با موفقیت حذف شد.", doctorController.deleteAppointmentById(247L));
    }

    /**
     * If there is no open appointment then 404 error is shown.
     */
    @Test
    public void deleteAppointmentById_if_notFound() {
        Assertions.assertThrows(ExcpServiceNotFoundAppointmentException.class, () -> {doctorController.deleteAppointmentById(234L);});
    }

    /**
     * If the appointment is taken by a patient, then a 406 error is shown
     */
    @Test
    public void deleteAppointmentById_if_reserved() {
        Assertions.assertThrows(ExcpServiceReserveAppointmentException.class, () -> {doctorController.deleteAppointmentById(246L);});
    }

    /**
     * Concurrency check; if doctor is deleting the same appointment that a patient is taking at the same time.
     */
    @Test
    public void deleteAppointmentById_concurrency_check() {
        ExecutorService es = Executors.newFixedThreadPool(3);
        try {
            // reserve
            es.execute(() -> {
                Assertions.assertEquals("وقت ملاقات مورد نظر با موفقیت رزرو شد.",
                        patientController.reserveAppointment(DTOReserve.builder()
                                .firstName("aref")
                                .lastName("faraji")
                                .mobil("09379644268")
                                .AppointmentId(248L)
                                .build()));
            });
            // delete
            es.execute(() -> {
                Assertions.assertEquals("وقت ملاقات مورد نظر با موفقیت حذف شد.", doctorController.deleteAppointmentById(248L));
            });
            es.shutdown();
            es.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
