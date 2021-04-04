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
 * @Date 2020-09-25 09:41:07
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MorderMessages  implements Serializable {

	private static final long serialVersionUID =  6776189266355392957L;

	private Long id;

	@NotNull(message = "评论的订单id不能为空")
	private Long orderid;

	private String time;

	@NotNull(message = "内容不能为空")
	private String content;

	@NotNull(message = "评论的订单类型不能为空")
	private Integer type;


}
