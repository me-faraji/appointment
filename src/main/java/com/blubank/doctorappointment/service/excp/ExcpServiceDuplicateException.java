package com.blubank.doctorappointment.service.excp;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class ExcpServiceDuplicateException extends ExcpService {
    public ExcpServiceDuplicateException(String errMessage) {
        super(409, ExcpServiceDuplicateException.class.getSimpleName(), errMessage);
    }
}
