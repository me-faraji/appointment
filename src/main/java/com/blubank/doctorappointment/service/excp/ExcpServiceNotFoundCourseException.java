package com.blubank.doctorappointment.service.excp;

public class ExcpServiceNotFoundCourseException extends ExcpService {
    public ExcpServiceNotFoundCourseException(String errMessage) {
        super(404, ExcpServiceNotFoundCourseException.class.getSimpleName(), errMessage);
    }
}