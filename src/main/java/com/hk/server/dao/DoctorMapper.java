package com.hk.server.dao;

import com.hk.server.dto.NewBaseMorderDTO;
import com.hk.server.dto.NewDetailMorderDTO;
import com.hk.server.entity.Admin;
import com.hk.server.entity.Doctor;
import com.hk.server.entity.Morder1;
import com.hk.server.entity.MorderMessages;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface DoctorMapper {
    int insertOne(Doctor doctor);

    List<Doctor> findAll(Map<String, Object> map);

    int changeState(int state, int id);

    Admin findDoctorByAccount(String account);

    Long findCount();

    int selectState(int id);

    List<Morder1> findMyMorder(Map<String, Object> map);

    List<MorderMessages> findMessageById(Long id);



    @Select("select * from morderMessages where orderid=#{id}")
    List<MorderMessages> getMorderMessages(@Param("id") Long id);

    @Insert("insert into morderMessages values(null,#{m.orderid},#{m.time},#{m.content},#{m.type})")
    int insertMorderMessage(@Param("m") MorderMessages m);
}
