package com.blubank.doctorappointment.service.excp;

public class ExcpServiceReserveAppointmentException extends ExcpService {
    public ExcpServiceReserveAppointmentException(String errMessage) {
        super(406, ExcpServiceReserveAppointmentException.class.getSimpleName(), errMessage);
    }
}
