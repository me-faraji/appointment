package com.blubank.doctorappointment.model;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tb_detail_course")
@Getter@Setter@AllArgsConstructor@NoArgsConstructor
public class DetailAppointmentModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String fromHour;
    private String toHour;
    private Integer status;
    @JoinColumn(name = "master_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private MasterAppointmentModel master;

    @JoinColumn(name = "patient_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private PatientModel patient;
}
