package com.blubank.doctorappointment.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter@Setter@ToString@AllArgsConstructor@NoArgsConstructor
@Entity
@Table(name = "tb_patient")
public class PatientModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "national_number")
    private String nationalNumber;
    @Column(name = "mobile")
    private String mobile;
}
