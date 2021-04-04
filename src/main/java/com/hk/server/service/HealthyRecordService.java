package com.hk.server.service;

import com.hk.server.dao.HealthyRecordMapper;
import com.hk.server.entity.Healthrecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: gg2020
 * @description:
 * @author: 头微凉
 * @create: 2020-10-09 20:03
 */
@Service
public class HealthyRecordService {
    @Autowired
    HealthyRecordMapper healthyRecordMapper;

    public Healthrecord findById(int id) {
        return healthyRecordMapper.findById(id);
    }
}