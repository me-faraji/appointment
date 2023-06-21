package com.blubank.doctorappointment.controller.excp;

import com.blubank.doctorappointment.common.ExcpGeneral;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter@Setter
public class ExcpController extends ExcpGeneral {
    public ExcpController(Integer errCode, String errType, String errMessage) {
        super(errCode, errType, errMessage);
    }
}
