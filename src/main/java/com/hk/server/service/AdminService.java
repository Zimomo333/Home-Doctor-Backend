package com.hk.server.service;

import com.hk.server.dao.AdminMapper;
import com.hk.server.dao.AuditMapper;
import com.hk.server.dao.DoctorMapper;
import com.hk.server.entity.Admin;
import com.hk.server.entity.Audit;
import com.hk.server.entity.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AdminService {

    @Autowired
    private AuditMapper auditMapper;

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private DoctorMapper docterMapper;

    public List<Audit> findAll(Map<String, Object> map) {
        return auditMapper.findAll(map);
    }

    public List<Doctor> findAlldoctor(Map<String, Object> map) {
        return docterMapper.findAll(map);
    }

    public int selectState(int id) {
        return docterMapper.selectState(id);
    }

    public Admin findAdminByAccount(String account) {
        return adminMapper.findAdminByAccount(account);
    }

    public int changeDoctorState(int state, int id) {
        return docterMapper.changeState(state, id);
    }
}
