package com.blubank.doctorappointment.controller.dto;

import com.blubank.doctorappointment.model.DetailCourseModel;
import lombok.*;

import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter@Setter@ToString@AllArgsConstructor@NoArgsConstructor@Builder
public class DTOMasterCourse implements Serializable {
    private Long id;
    private Date date;
    private Integer status;
    private String description;
    private List<DTODetailCourse> detail;
}
