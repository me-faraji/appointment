package com.blubank.doctorappointment.model;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter@Setter@AllArgsConstructor@NoArgsConstructor
@Entity
@Table(name = "tb_master_course")
//@NamedEntityGraph(name = "MasterCourseModel.detail",
//        attributeNodes = @NamedAttributeNode("detail")
//)
public class MasterCourseModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "master_id")
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    private Integer capacity;
    private Integer countEmpty;
    private Integer countReserve;
    private Integer countDischarge;
    private Integer countDelete;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "master")
    private List<DetailCourseModel> detail;
}
