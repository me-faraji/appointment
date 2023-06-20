package com.blubank.doctorappointment.controller.dto;

import com.blubank.doctorappointment.model.DetailCourseModel;
import lombok.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter@Setter@ToString@AllArgsConstructor@NoArgsConstructor@Builder
public class DTOMasterCourse implements Serializable {
    private Long id;
    private Date date;
    private Integer fromHour;
    private Integer toHour;
    private Boolean status;
    private String description;
    private List<DTODetailCourse> detail;
}
