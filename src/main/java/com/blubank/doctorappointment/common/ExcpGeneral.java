package com.blubank.doctorappointment.common;

import lombok.*;

import java.io.Serializable;

@Getter@Setter@AllArgsConstructor@NoArgsConstructor
public class ExcpGeneral extends RuntimeException implements Serializable {

    private Integer errCode;
    private String errType;
    private String errMessage;

    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
