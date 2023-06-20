package com.blubank.doctorappointment.model;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@Getter@Setter@ToString@AllArgsConstructor@NoArgsConstructor
@Table(name = "tb_detail_course")
public class DetailCourseModel implements Serializable {
    @Id
    @Column(name = "id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "master")
    private MasterCourseModel master;
    @Column(name = "from_hour")
    private String fromHour;
    @Column(name = "to_hour")
    private String toHour;
    @Column(name = "status")
    private String status;
    @Column(name = "patient-mobil")
    private String patientMobil;
    @Column(name = "description")
    private String description;
}
