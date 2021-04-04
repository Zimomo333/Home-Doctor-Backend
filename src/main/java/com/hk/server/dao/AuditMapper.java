package com.hk.server.dao;

import com.hk.server.entity.Audit;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface AuditMapper {

    List<Audit> findAll(Map<String,Object> map);

    Integer insertOnce(Audit audit);

    Integer deleteOne(long id);

    Audit findById(int id);
}
