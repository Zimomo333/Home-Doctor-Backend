package com.hk.server.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import javax.validation.constraints.*;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description   实体类
 * @Author  夜御代码300次
 * @Date 2020-10-05 11:05:12
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Healthyabout1  implements Serializable {

    private static final long serialVersionUID =  4874280836920700359L;

    private String id;

    @NotNull(message = "内容不能为空")
    private String content;

    @NotNull(message = "评论的订单类型不能为空")
    private String type;

    private Long adminid;

    private String time;

    private String title;

    public void setTime(String time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (time.contains("-")) {
            try {
                Date parse = simpleDateFormat.parse(time);
                this.time = String.valueOf(parse.getTime());
            } catch (Exception e) {
                this.time = String.valueOf(new Date().getTime());
            }
        }
        else {
            try {
                this.time = simpleDateFormat.format(new Date(Long.parseLong(time)));
            }catch (Exception e) {
                this.time =simpleDateFormat.format(new Date());
            }
        }
    }
}
