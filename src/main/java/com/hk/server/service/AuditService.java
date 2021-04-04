package com.hk.server.service;

import com.hk.server.dao.AuditMapper;
import com.hk.server.entity.Audit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: gg2020
 * @description: 审核表服务类
 * @author: 头微凉
 * @create: 2020-09-11 08:56
 */
@Service
public class AuditService {

    @Autowired
    AuditMapper auditMapper;

    //一次插入一条审核记录
    public int insertOnce(Audit audit)
    {
        return auditMapper.insertOnce(audit);
    }

    public int deleteOne(long id){return auditMapper.deleteOne(id);}

    public Audit findById(int id)
    {
        return auditMapper.findById(id);
    }
}