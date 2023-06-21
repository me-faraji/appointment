package com.blubank.doctorappointment.service.excp;

import com.blubank.doctorappointment.common.ExcpGeneral;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class ExcpService extends ExcpGeneral {
    public ExcpService(Integer errCode, String errType, String errMessage) {
        super(errCode, errType, errMessage);
    }
}
