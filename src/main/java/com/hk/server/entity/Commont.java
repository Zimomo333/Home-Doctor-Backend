package com.hk.server.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import javax.validation.constraints.*;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * @Description   实体类
 * @Author  夜御代码300次
 * @Date 2020-09-25 09:41:07 
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Commont  implements Serializable {

	private static final long serialVersionUID =  1207769667839147593L;

	private Long id;

	@NotNull(message = "评论的订单id不能为空")
	private Long orderid;

	@NotNull(message = "内容不能为空")
	private String content;

	private Long star;

	@NotNull(message = "评论的订单类型不能为空")
	private Long type;

	private Long doctorid;

}
