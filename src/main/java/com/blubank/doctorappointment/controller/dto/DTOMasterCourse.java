package com.blubank.doctorappointment.controller.dto;

import lombok.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter@Setter@AllArgsConstructor@NoArgsConstructor@Builder
public class DTOMasterCourse implements Serializable {
    private Long id;
    private Date date;
    private Integer capacity;
    private Integer countEmpty;
    private Integer countReserve;
    private Integer countDischarge;
    private Integer countDelete;
    private List<DTODetailCourse> detail;
}
