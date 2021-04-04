package com.hk.server.dao;

import com.hk.server.entity.Healthyabout;
import com.hk.server.entity.Healthyabout1;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface ArticleMapper {
    int insertOne(Healthyabout one);

    List<Healthyabout1> findAll(Map<String, Object> map);

    int deleteOne(long id, int adminid);

    int updateOne(Healthyabout healthyabout);
}
