package com.blubank.doctorappointment.model;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@Getter@Setter@AllArgsConstructor@NoArgsConstructor
@Entity
@Table(name = "tb_detail_course")
public class DetailCourseModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "master_id")
    private MasterCourseModel master;
    @Column(name = "from_hour")
    private String fromHour;
    @Column(name = "to_hour")
    private String toHour;
    private String status;
    @Column(name = "patient_mobil")
    private String patientMobil;
    private String description;
}
