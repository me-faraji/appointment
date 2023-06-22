package com.blubank.doctorappointment.service.excp;

public class ExcpServiceNotFoundAppointmentException extends ExcpService {
    public ExcpServiceNotFoundAppointmentException(String errMessage) {
        super(404, ExcpServiceNotFoundAppointmentException.class.getSimpleName(), errMessage);
    }
}