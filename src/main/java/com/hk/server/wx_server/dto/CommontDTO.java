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
public class CommontDTO implements Serializable {

    private Long id;

    private Long orderid;

    private String content;

    private Long star;

    private Long type;

    private Long doctorid;

    private String time;

}
