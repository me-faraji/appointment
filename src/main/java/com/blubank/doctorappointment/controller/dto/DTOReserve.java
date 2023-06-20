package com.blubank.doctorappointment.controller.dto;

import lombok.*;

@Getter@Setter@ToString@AllArgsConstructor@NoArgsConstructor@Builder
public class DTOReserve {
    private String firstName;
    private String lastName;
    private String nationalNumber;
    private String mobile;
    private Long detailCourseId;
}
