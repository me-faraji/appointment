select mastercour0_.master_id       as master_i1_1_0_,
       detail1_.id                  as id1_0_1_,
       mastercour0_.capacity        as capacity2_1_0_,
       mastercour0_.count_delete    as count_de3_1_0_,
       mastercour0_.count_discharge as count_di4_1_0_,
       mastercour0_.count_empty     as count_em5_1_0_,
       mastercour0_.count_reserve   as count_re6_1_0_,
       mastercour0_.date            as date7_1_0_,
       mastercour0_.description     as descript8_1_0_,
       detail1_.description         as descript2_0_1_,
       detail1_.from_hour           as from_hou3_0_1_,
       detail1_.master_id           as master_i6_0_1_,
       detail1_.patient_id          as patient_7_0_1_,
       detail1_.status              as status4_0_1_,
       detail1_.to_hour             as to_hour5_0_1_
from tb_master_course mastercour0_
         inner join tb_detail_course detail1_ on mastercour0_.master_id = detail1_.master_id
where mastercour0_.date = ?
  and detail1_.status = ? limit ?