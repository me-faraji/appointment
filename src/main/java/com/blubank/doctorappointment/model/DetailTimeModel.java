package com.blubank.doctorappointment.model;

import lombok.*;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Getter@Setter@ToString@AllArgsConstructor@NoArgsConstructor
@Table(name = "tb_detail_time")
public class DetailTimeModel implements Serializable {
    @Id
    private Long id;
    private Long masterId;
    private String fromHour;
    private String toHour;
    private String status;
    private String mobil;
}
