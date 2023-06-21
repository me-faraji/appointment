package com.blubank.doctorappointment.controller.dto;

import lombok.*;

@Getter@Setter@AllArgsConstructor@NoArgsConstructor@Builder
public class DTOPatient {
    private Long id;
    private String firstName;
    private String lastName;
    private String mobile;
}
