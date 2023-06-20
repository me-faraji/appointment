package com.blubank.doctorappointment.model;

import lombok.*;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Getter@Setter@ToString@AllArgsConstructor@NoArgsConstructor
@Table(name = "tb_master_time")
public class MasterCourseModel implements Serializable {
    @Id
    private Long id;
    private Date date;
    private Integer fromHour;
    private Integer toHour;
    private Boolean status;
}
