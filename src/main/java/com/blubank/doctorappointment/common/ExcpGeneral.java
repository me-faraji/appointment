package com.blubank.doctorappointment.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter@Setter@AllArgsConstructor@NoArgsConstructor
public class ExcpGeneral extends Exception implements Serializable {

    private String errCode;
    private String errType;
    private String errMassage;

    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
