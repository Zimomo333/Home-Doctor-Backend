package com.hk.server.service;

import com.hk.server.dao.DoctorMapper;
import com.hk.server.entity.Admin;
import com.hk.server.entity.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: gg2020
 * @description: 医生服务类
 * @author: 头微凉
 * @create: 2020-09-18 10:06
 */
@Service
public class DoctorService {
    @Autowired
    DoctorMapper doctorMapper;

    public int inserOne(Doctor one) {
        return doctorMapper.insertOne(one);
    }

    public Admin findDocterByAccount(String account) {
        return doctorMapper.findDoctorByAccount(account);
    }


    public Long findCount() {
        return doctorMapper.findCount();
    }

}