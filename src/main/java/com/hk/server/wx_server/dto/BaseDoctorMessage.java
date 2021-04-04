package com.hk.server.wx_server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BaseDoctorMessage {

    private Long id;

    private String realName;

    private String province;

    private String area;

    private String hospital;

    private String message;

    private String skills;

    private Double morderPrice;

    private Double porderPrice;

    private Long services;

    private Long allcommonts;

    private Long goodcommonts;

    private String head;

    private Long state;


}
