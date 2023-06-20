package com.blubank.doctorappointment.model;

import lombok.*;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Getter@Setter@ToString@AllArgsConstructor@NoArgsConstructor
@Table(name = "tb_patient")
public class PatientModel implements Serializable {
    @Id
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
