package com.hk.server.service;

import com.hk.server.dao.ArticleMapper;
import com.hk.server.entity.Healthyabout;
import com.hk.server.entity.Healthyabout1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @program: gg2020
 * @description: 有关文章的服务层
 * @author: 头微凉
 * @create: 2020-09-27 09:42
 */
@Service
public class ArticleService {
    @Autowired
    ArticleMapper mapper;

    public int insertOne(Healthyabout one) {
        return mapper.insertOne(one);
    }

    public List<Healthyabout1> findAll(Map<String, Object> map) {
        return mapper.findAll(map);
    }

    public int deleteOne(long id, int adminid) {
        return mapper.deleteOne(id, adminid);
    }

    public int updateOne(Healthyabout healthyabout) {
        return mapper.updateOne(healthyabout);
    }
}