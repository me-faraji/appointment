package com.blubank.doctorappointment.model;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tb_detail_course")
@Getter@Setter@AllArgsConstructor@NoArgsConstructor
public class DetailCourseModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String fromHour;
    private String toHour;
    private Integer status;
    private String description;
    @JoinColumn(name = "master_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private MasterCourseModel master;

    @JoinColumn(name = "patient_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private PatientModel patient;
}
