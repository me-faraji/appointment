package com.blubank.doctorappointment.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter@Setter@AllArgsConstructor@NoArgsConstructor
@Entity
@Table(name = "tb_patient")
public class PatientModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "patient_id")
    private Long id;
    private String firstName;
    private String lastName;
    private String mobil;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "patient")
    private List<DetailCourseModel> course;
}
