package com.blubank.doctorappointment.model.enums;

import lombok.Getter;

@Getter
public enum EDetailCourseStatus {
    EMPTY(101, "empty", "خالی"),
    RESERVE(201, "reserve", "رزرو"),
    DISCHARGE(301, "discharge", "ترخیص"),
    DELETE(401, "delete", "حذف");

    private final int code;
    private final String eName;
    private final String pName;
    EDetailCourseStatus(int code, String eName, String pName) {
        this.code = code;
        this.eName = eName;
        this.pName = pName;
    }
    public static EDetailCourseStatus getValue(int code) {
        EDetailCourseStatus[] arr$ = values();
        int len$ = arr$.length;
        for(int i$ = 0; i$ < len$; ++i$) {
            EDetailCourseStatus item = arr$[i$];
            if (item.getCode() == code) {
                return item;
            }
        }
        return null;
    }
}
