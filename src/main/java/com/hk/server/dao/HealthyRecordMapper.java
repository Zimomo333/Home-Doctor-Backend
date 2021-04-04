package com.hk.server.dao;

import com.hk.server.entity.Healthrecord;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface HealthyRecordMapper {
    Healthrecord findById(int id);
}
