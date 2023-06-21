package com.blubank.doctorappointment.model;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter@Setter@AllArgsConstructor@NoArgsConstructor@Builder
@Entity
@Table(name = "tb_master_course")
public class MasterCourseModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "from_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fromDate;
    @Column(name = "to_Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date toDate;
    private Boolean status;
    private String description;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "master")
    private List<DetailCourseModel> detail;
}
