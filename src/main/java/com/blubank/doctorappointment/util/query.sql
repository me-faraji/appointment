select detailcour0_.id         as id1_0_0_,
       detailcour0_.from_hour  as from_hou2_0_0_,
       detailcour0_.master_id  as master_i5_0_0_,
       detailcour0_.patient_id as patient_6_0_0_,
       detailcour0_.status     as status3_0_0_,
       detailcour0_.to_hour    as to_hour4_0_0_
from tb_detail_course detailcour0_
where detailcour0_.id = ? for share