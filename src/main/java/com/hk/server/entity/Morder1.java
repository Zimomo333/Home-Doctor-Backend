package com.hk.server.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.*;

import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description 实体类
 * @Author 夜御代码300次
 * @Date 2020-09-25 09:41:07
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Morder1 implements Serializable {

    private static final long serialVersionUID = 8364841854690310693L;

    private Long id;

    private String beginTime;

    private String activeTime;

    @NotNull(message = "内容不能为空")
    private String content;

    private Integer hasPicture;

    private Double price;

    private Integer userConfirm;

    private String userCtime;

    private String username;

    private Long doctorid;

    private Integer iscommont;

    public void setBeginTime(String beginTime) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (beginTime.contains("-")) {
            try {
                Date parse = simpleDateFormat.parse(beginTime);
                this.beginTime = String.valueOf(parse.getTime());
            } catch (Exception e) {
                this.beginTime = String.valueOf(new Date().getTime());
            }
        } else {
            try {
                this.beginTime = simpleDateFormat.format(new Date(Long.parseLong(beginTime)));
            } catch (Exception e) {
                this.beginTime = simpleDateFormat.format(new Date());
            }
        }
    }

    public void setActiveTime(String activeTime) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (activeTime.contains("-")) {
            try {
                Date parse = simpleDateFormat.parse(activeTime);
                this.activeTime = String.valueOf(parse.getTime());
            } catch (Exception e) {
                this.activeTime = String.valueOf(new Date().getTime());
            }
        } else {
            try {
                this.activeTime = simpleDateFormat.format(new Date(Long.parseLong(activeTime)));
            } catch (Exception e) {
                this.activeTime = simpleDateFormat.format(new Date());
            }
        }
    }

    public void setUserCtime(String userCtime) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (userCtime.contains("-")) {
            try {
                Date parse = simpleDateFormat.parse(userCtime);
                this.userCtime = String.valueOf(parse.getTime());
            } catch (Exception e) {
                this.userCtime = String.valueOf(new Date().getTime());
            }
        } else {
            try {
                this.userCtime = simpleDateFormat.format(new Date(Long.parseLong(userCtime)));
            } catch (Exception e) {
                this.userCtime = simpleDateFormat.format(new Date());
            }
        }
    }

}
