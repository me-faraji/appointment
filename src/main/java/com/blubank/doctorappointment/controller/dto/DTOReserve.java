package com.blubank.doctorappointment.controller.dto;

import lombok.*;

@Getter@Setter@AllArgsConstructor@NoArgsConstructor@Builder
public class DTOReserve {
    private String firstName;
    private String lastName;
    private String mobil;
    private Long AppointmentId;
}
