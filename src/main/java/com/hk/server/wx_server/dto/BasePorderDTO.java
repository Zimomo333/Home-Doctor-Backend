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
public class BasePorderDTO implements Serializable {

    private Long id;

    private String realName;

    private String orderTime;

    private String freedTime;

    private Double price;



}
