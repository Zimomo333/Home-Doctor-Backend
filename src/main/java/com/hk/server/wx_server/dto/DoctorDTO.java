package com.hk.server.wx_server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDTO  implements Serializable {

    private Long id;

    private Integer sex;

    private String realName;

    private String province;

    private String area;

    private String address;

    private String message;

    private String background;

    private String hospital;

    private String department;

    private String deputyDeparment;

    private String professor;

    private String skills;

    private Double morderPrice;

    private Double porderPrice;

    private Long services;

    private Long allcommonts;

    private Long goodcommonts;

    private Double balance;

    private Long state;

    private String email;

    private String password;

    private String head;

}
