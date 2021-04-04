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
public class Porder1 implements Serializable {

    private static final long serialVersionUID = 5771101335576048381L;

    private Long id;

    private String username;

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

    public void setOrderTime(String orderTime) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (orderTime.contains("-")) {
            try {
                Date parse = simpleDateFormat.parse(orderTime);
                this.orderTime = String.valueOf(parse.getTime());
            } catch (Exception e) {
                this.orderTime = String.valueOf(new Date().getTime());
            }
        } else {
            try {
                this.orderTime = simpleDateFormat.format(new Date(Long.parseLong(orderTime)));
            } catch (Exception e) {
                this.orderTime = simpleDateFormat.format(new Date());
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
