package com.blubank.doctorappointment;

import com.blubank.doctorappointment.controller.DoctorController;
import com.blubank.doctorappointment.controller.dto.DTODetailAppointment;
import com.blubank.doctorappointment.controller.excp.ExcpControllerInvalidParameterException;
import com.blubank.doctorappointment.service.excp.ExcpServiceDuplicateException;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.hamcrest.Matchers.*;
import java.util.List;

@SpringBootTest
public class DoctorControllerTest {
    @Autowired
    DoctorController doctorController;

    // -------------------------------------------------------------- create Appointment ------------------------------------
    /**
     * TestCase: Define Appointment correctly
     */
    @Test
    public void creareAppointment_normal() throws Exception {
        Assertions.assertEquals("تعریف دوره با موفقیت انجام شد.",
                doctorController.createAppointment("10-01-2018 10:00:00", "10-01-2018 14:00:00"));
    }

    /**
     * TestCase: If doctor enter invalid syntax of startDate or endDate,
     * throws ExcpControllerInvalidParameterException
     * errMessage: لطفا پارامترها را صحیح وارد نمائید.
     */
    @Test
    public void creareAppointment_if_invalid_date() {
        Assertions.assertThrows(ExcpControllerInvalidParameterException.class,
                () -> {doctorController.createAppointment("10-01-201810:00:00", "10-012018 14:00:00");});
    }

    /**
     * TestCase: If doctor enters an endDate that is sooner than startDate,
     * throws ExcpControllerInvalidParameterException
     * errMessage: از تاریخ بایستی بزرگتر از تا تاریخ باشد.
     */
    @Test
    public void creareAppointment_if_startDate_greaterThan_endDate() {
        Assertions.assertThrows(ExcpControllerInvalidParameterException.class,
                () -> {doctorController.createAppointment("10-01-2018 14:00:00", "10-01-2018 10:00:00");});
    }

    /**
     * TestCase: If interval between the startDate and endDate is more than two days,
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
     * errMessage: هر دوره حداقل 30 دقیقه می باشد.
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
     * TestCase: If there is no appointment set,
     * result: list or empty
     */
    @Test
    public void creareAppointment_fetch_reserved_appointment() throws Exception {
        List<DTODetailAppointment> result = doctorController.getReserveAppointment("10-01-2018");
        MatcherAssert.assertThat("result must be Null or List", result, anyOf(nullValue(), isA(List.class)));
    }

}
