package com.blubank.doctorappointment.service.excp;

public class ExcpServiceReserveCourseException extends ExcpService {
    public ExcpServiceReserveCourseException(String errMessage) {
        super(406, ExcpServiceReserveCourseException.class.getSimpleName(), errMessage);
    }
}
