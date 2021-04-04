package com.hk.server.wx_server.dao;

import com.hk.server.entity.*;
import com.hk.server.wx_server.dto.*;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import javax.print.Doc;
import java.util.List;

@Repository
@Mapper
public interface UserMapper {

    @Select("select * from user where wx_open_id=#{openid}")
    User getUserByOpenid(@Param("openid")String openid);

    @Insert("insert into user(username,wx_open_id,phone) values(#{user.username},#{user.wxOpenId},#{user.phone})")
    int insertUser(@Param("user")User user);

    @Insert("insert into healthrecord(userid) values(#{id})")
    int insertInitHealthRecord(@Param("id")Long id);

    @Select("select * from healthrecord where userid=#{userid}")
    Healthrecord getHealthRecordByUserId(@Param("userid")Long id);

    @Update("update healthrecord set username=#{rc.username},sex=#{rc.sex},birthdate=#{rc.birthdate},height=#{rc.height}," +
            "issmoke=#{rc.issmoke},isdrink=#{rc.isdrink},liver_state=#{rc.liverState},ismarry=#{rc.ismarry},chronic_disease=#{rc.chronicDisease}," +
            "other=#{rc.other},allergy=#{rc.allergy},history_disease=#{rc.historyDisease},weight=#{rc.weight}  " +
            "where id=#{rc.id}")
    int updateHealthRecord(@Param("rc")Healthrecord record);

    @Select("select id,real_name,hospital,message,skills,morder_price,porder_price,services,allcommonts,goodcommonts,province,area,state " +
            "from doctor where department=#{department} limit ${start},10")
    List<BaseDoctorMessage> findDoctorByDepartment(@Param("department") String department,@Param("start") Integer start);

    @Select("select * from doctor where id=#{id}")
    DoctorDTO findDoctorMessageById(@Param("id")Long id);

    @Insert("insert into doctor values(null,#{doctor.sex},#{doctor.realName},#{doctor.province},#{doctor.area}," +
            "#{doctor.address},#{doctor.message},#{doctor.background},#{doctor.hospital},#{doctor.department},#{doctor.deputyDeparment}," +
            "#{doctor.professor},#{doctor.skills},#{doctor.morderPrice},#{doctor.porderPrice},#{doctor.services},#{doctor.allcommonts}," +
            "#{doctor.goodcommonts},#{doctor.balance},#{doctor.state},#{doctor.email},#{doctor.password})")
    int insertDoctorMessage(@Param("doctor") Doctor doctor);

    @Select("select * from commont where doctorid=#{id}")
    List<Commont> findAllCommontByDoctorid(@Param("id") Long id);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("insert into morder values(null,#{morder.beginTime},#{morder.activeTime},#{morder.content}," +
            "null,#{morder.price},#{morder.userConfirm},null,#{morder.userid},#{morder.doctorid},#{morder.iscommont})")
    void insertMorder(@Param("morder")Morder morder);

    @Select("select * from morder where id=#{id}")
    Morder findMorder(@Param("id")Long id);

    @Select("select a.*,b.real_name from morder a,doctor b where a.doctorid=b.id and a.id=#{id}")
    DetailMorderDTO findDetailMorder(@Param("id")Long id);

    @Update("update morder set user_confirm=1,user_ctime=#{time} where id=#{id} and user_confirm=0")
    int confirmMorder(@Param("id")Long id,@Param("time")String time);

    @Update("update doctor set services=services+1 where id=#{id}")
    int increDoctorServices(@Param("id")Long id);

    @Select("select c.id,b.real_name,c.price,c.begin_time,c.active_time " +
            "from  user a,doctor b,morder c " +
            "where c.userid=a.id and c.doctorid=b.id and user_confirm=0  and iscommont=0 and a.id=#{id} order by begin_time desc")
    List<BaseMorderDTO> findOngoingMorder(@Param("id") Long id);

    @Select("select c.id,b.real_name,c.price,c.begin_time,c.active_time " +
            "from  user a,doctor b,morder c " +
            "where c.userid=a.id and c.doctorid=b.id and user_confirm=1 and iscommont=0 and a.id=#{id} order by begin_time desc")
    List<BaseMorderDTO> findEvaluateMorder(@Param("id")Long id);

    @Select("select c.id,b.real_name,c.price,c.begin_time,c.active_time " +
            "from  user a,doctor b,morder c " +
            "where c.userid=a.id and c.doctorid=b.id and user_confirm=1 and iscommont=1 and a.id=#{id} order by begin_time desc")
    List<BaseMorderDTO> findCompleteMorder(@Param("id")Long id);

    @Select("select * from commont where doctorid=#{id} order by time desc limit ${start},10")
    List<CommontDTO> findCommontByDoctorId(@Param("id")Long id,@Param("start") Integer start);

    @Select("select * from morderMessages where orderid=#{id}")
    List<MorderMessages> findAllMorderMessage(@Param("id")Long id);

    @Insert("insert into morderMessages values(null,#{ms.orderid},#{ms.time},#{ms.content},#{ms.type})")
    int replyMorderMessage(@Param("ms")MorderMessages ms);

    @Select("select doctorid from morder where id=#{id}")
    Long findDoctorIdByMorderId(@Param("id")Long id);

    @Select("select doctorid from porder where id=#{id}")
    Long findDoctorIdByPorderId(@Param("id")Long id);

    @Insert("insert into commont values(null,#{c.orderid},#{c.content},#{c.star},#{c.type},#{c.doctorid},#{c.time})")
    int insertCommont(@Param("c")CommontDTO c);

    @Update("update morder set iscommont=1 where id=#{id}")
    int evaluateMorder(@Param("id")Long id);

    @Update("update doctor set allcommonts=allcommonts+1,goodcommonts=goodcommonts+1 where id=#{id}")
    int updateDoctorCommontAndGood(@Param("id")Long id);

    @Update("update doctor set allcommonts=allcommonts+1 where id=#{id}")
    int updateDoctorCommont(@Param("id")Long id);

    @Insert("insert into porder values(null,#{p.orderTime},#{p.userPhone},#{p.freedTime}," +
            "#{p.userConfirm},null,#{p.price},#{p.userid},#{p.doctorid},#{p.iscommont},#{p.doctorConfirm})")
    int insertPorder(@Param("p")Porder p);

    @Select("select b.id,a.real_name,b.order_time,b.freed_time,b.price " +
            "from doctor a,porder b " +
            "where a.id=b.doctorid and b.userid=#{userid} and b.user_confirm=0 and b.iscommont=0  " +
            "order by b.order_time desc")
    List<BasePorderDTO> findGoingPorder(@Param("userid")Long userid);

    @Select("select b.id,a.real_name,b.order_time,b.freed_time,b.price " +
            "from doctor a,porder b " +
            "where a.id=b.doctorid and b.userid=#{userid} and b.user_confirm=1 and b.iscommont=0  " +
            "order by b.order_time desc")
    List<BasePorderDTO> findEvaluatePorder(@Param("userid")Long userid);

    @Select("select b.id,a.real_name,b.order_time,b.freed_time,b.price " +
            "from doctor a,porder b " +
            "where a.id=b.doctorid and b.userid=#{userid} and b.user_confirm=1 and b.iscommont=1  " +
            "order by b.order_time desc")
    List<BasePorderDTO> findCompletePorder(@Param("userid")Long userid);

    @Select("select * from porder where id=#{id}")
    Porder findPorderById(@Param("id")Long id);

    @Select("select a.*,b.real_name from porder a,doctor b where a.doctorid=b.id and a.id=#{id}")
    DetailPorderDTO findDetailPorderById(@Param("id")Long id);

    @Update("update porder set user_confirm=1,user_ctime=#{time} where id=#{id} and user_confirm=0")
    int confirmPorder(@Param("id")Long id,@Param("time")String time);

    @Select("select * from commont where orderid=#{id}")
    CommontDTO getCommontById(@Param("id")Long id);

    @Update("update porder set iscommont=1 where id=#{id}")
    int evaluatePorder(@Param("id")Long id);

    @Select("select id,real_name,hospital,message,skills,morder_price,porder_price,services,allcommonts,goodcommonts,province,area,state " +
            "from doctor where department=#{department} order by goodcommonts desc limit ${start},10")
    List<BaseDoctorMessage> findDoctorOrderByGoodCommonts(@Param("department") String department,@Param("start") Integer start);

    @Select("select id,real_name,hospital,message,skills,morder_price,porder_price,services,allcommonts,goodcommonts,province,area,state " +
            "from doctor where department=#{department} order by services desc limit ${start},10")
    List<BaseDoctorMessage> findDoctorOrderByServices(@Param("department") String department,@Param("start") Integer start);

    @Select("select id,real_name,hospital,message,skills,morder_price,porder_price,services,allcommonts,goodcommonts,province,area,state " +
            "from doctor where department=#{department} order by morder_price asc limit ${start},10")
    List<BaseDoctorMessage> findDoctorOrderByPriceAsc(@Param("department") String department,@Param("start") Integer start);

    @Select("select id,real_name,hospital,message,skills,morder_price,porder_price,services,allcommonts,goodcommonts,province,area,state " +
            "from doctor where department=#{department} order by morder_price desc limit ${start},10")
    List<BaseDoctorMessage> findDoctorOrderByPriceDesc(@Param("department") String department,@Param("start") Integer start);

    @Select("select id,real_name,hospital,message,skills,morder_price,porder_price,services,allcommonts,goodcommonts,province,area,state " +
            "from doctor where department=#{department} and province like #{province} and area like #{city} limit ${start},10")
    List<BaseDoctorMessage> findDoctorOrderByPlace(@Param("department") String department,@Param("start") Integer start,
                                                   @Param("province")String province,@Param("city")String city);

    @Select("select wx_open_id from user where id=#{id}")
    String getOpenIdByUserId(@Param("id")Long id);

    @Select("select id,real_name,hospital,message,skills,morder_price,porder_price,services,allcommonts,goodcommonts,province,area,state " +
            "from doctor where real_name like '%${keyword}%' or hospital like '%${keyword}%' or department like '%${keyword}%' limit ${start},10")
    List<BaseDoctorMessage> searchDoctorByKeyWord(@Param("keyword")String keyword,@Param("start") Integer start);

    @Select("select id,real_name,hospital,message,skills,morder_price,porder_price,services,allcommonts,goodcommonts,province,area,state " +
            "from doctor where skills like '%${keyword}%' limit ${start},10")
    List<BaseDoctorMessage> searchDoctorBySkill(@Param("keyword")String keyword,@Param("start") Integer start);

    @Select("select id,type,time,title from healthyabout order by time desc limit ${start},5")
    List<BaseHealthyAbout> findAllHealthyAbouts(@Param("start")Integer start);

    @Select("select id,content,type,time,title from healthyabout where id=#{id}")
    DetailHealthyAbout findHealthyAboutById(@Param("id")Long id);
}
