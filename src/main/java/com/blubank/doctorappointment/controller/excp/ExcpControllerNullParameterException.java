package com.blubank.doctorappointment.controller.excp;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class ExcpControllerNullParameterException extends ExcpController {
    public ExcpControllerNullParameterException(String errMessage) {
        super(400, ExcpControllerNullParameterException.class.getSimpleName(), errMessage);
    }

}
