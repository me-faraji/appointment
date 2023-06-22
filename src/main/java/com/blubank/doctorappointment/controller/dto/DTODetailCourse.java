package com.blubank.doctorappointment.controller.dto;

import lombok.*;
import java.io.Serializable;

@Getter@Setter@AllArgsConstructor@NoArgsConstructor@Builder
public class DTODetailCourse implements Serializable {
    private Long id;
    private String fromHour;
    private String toHour;
    private String status;
    private DTOPatient patient;

    @Getter@Setter@AllArgsConstructor@NoArgsConstructor@Builder
    public static class DTOPatient implements Serializable{
        private Long id;
        private String firstName;
        private String lastName;
        private String mobil;
    }
}
