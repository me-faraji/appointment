package com.blubank.doctorappointment.model;

import lombok.*;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Getter@Setter@ToString@AllArgsConstructor@NoArgsConstructor
@Table(name = "tb_patient")
public class PatientModel implements Serializable {
    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private String nationalNumber;
    private String mobile;
}
