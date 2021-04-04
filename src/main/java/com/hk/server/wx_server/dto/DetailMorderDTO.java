package com.hk.server.wx_server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DetailMorderDTO implements Serializable {

    private Long id;

    private String beginTime;

    private String activeTime;

    @NotNull(message = "内容不能为空")
    private String content;

    private Integer hasPicture;

    private Double price;

    private Integer userConfirm;

    private String userCtime;

    private Long userid;

    private Long doctorid;

    private Integer iscommont;

    private String realName;

}
