package com.hk.server.service;

import com.hk.server.dao.PhoneOrderMapper;
import com.hk.server.entity.Porder;
import com.hk.server.entity.Porder1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @program: gg2020
 * @description: 电话订单服务类
 * @author: 头微凉
 * @create: 2020-10-06 15:04
 */
@Service
public class PorderService {
    @Autowired
    PhoneOrderMapper phoneOrderMapper;

    public List<Porder1> findMyPorders(Map<String,Object> map) {
        return phoneOrderMapper.findMyPorder(map);
    }

    public int confirm(int orderid, int account) {
        return phoneOrderMapper.confirm(orderid, account);
    }
}