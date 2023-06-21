package com.blubank.doctorappointment.controller.excp;

public class ExcpControllerInvalidParameterException extends ExcpController {
    public ExcpControllerInvalidParameterException(String errMessage) {
        super(400, ExcpControllerInvalidParameterException.class.getSimpleName(), errMessage);
    }
}
