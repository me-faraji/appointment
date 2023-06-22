package com.blubank.doctorappointment.controller.dto;

import lombok.*;
import java.io.Serializable;
import java.util.List;

@Getter@Setter@AllArgsConstructor@NoArgsConstructor@Builder
public class DTOPatient implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private String mobil;
    private List<DTODetailCourse> course;

    @Getter@Setter@AllArgsConstructor@NoArgsConstructor@Builder
    public static class DTODetailCourse implements Serializable {
        private Long id;
        private String fromHour;
        private String toHour;
        private String status;
    }
}
