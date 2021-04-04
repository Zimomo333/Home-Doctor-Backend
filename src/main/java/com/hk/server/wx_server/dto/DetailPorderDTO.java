package com.hk.server.wx_server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuppressWarnings("all")
public class DetailPorderDTO implements Serializable {

    private Long id;

    private String orderTime;

    private String userPhone;

    private String freedTime;

    private Integer userConfirm;

    private String userCtime;

    private Double price;

    private Long userid;

    private Long doctorid;

    private Integer iscommont;

    private Integer doctorConfirm;

    private String realName;


}
