package com.hk.server.dao;

import com.hk.server.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AdminMapper {

    Admin findAdminByAccount(String account);

    List<Admin> findAll();

}
