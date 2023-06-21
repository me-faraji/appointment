select mastercour0_.master_id   as master_i1_1_0_,
       mastercour0_.description as descript2_1_0_,
       mastercour0_.from_date   as from_dat3_1_0_,
       mastercour0_.status      as status4_1_0_,
       mastercour0_.to_date     as to_date5_1_0_,
       detail1_.master_id       as master_i7_0_1_,
       detail1_.id              as id1_0_1_,
       detail1_.id              as id1_0_2_,
       detail1_.description     as descript2_0_2_,
       detail1_.from_hour       as from_hou3_0_2_,
       detail1_.master_id       as master_i7_0_2_,
       detail1_.patient_mobil   as patient_4_0_2_,
       detail1_.status          as status5_0_2_,
       detail1_.to_hour         as to_hour6_0_2_
from tb_master_course mastercour0_
         left outer join tb_detail_course detail1_ on mastercour0_.master_id = detail1_.master_id
where mastercour0_.master_id = ?