package com.hk.server.dao;

import com.hk.server.entity.Porder;
import com.hk.server.entity.Porder1;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface PhoneOrderMapper {
    List<Porder1> findMyPorder(Map<String,Object> map);

    int confirm(int orderid, int account);
}
