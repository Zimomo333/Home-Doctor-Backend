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
public class BaseMorderDTO implements Serializable {

    private Long id;

    private String realName;

    private Double price;

    private String beginTime;

    private String activeTime;


}
