package com.blubank.doctorappointment.controller.dto;

import lombok.*;
import java.util.Date;

@Getter@Setter@AllArgsConstructor@NoArgsConstructor@Builder
public class DTOStartCourse {
    private Date date;
    private Integer fromHour;
    private Integer toHour;
}
