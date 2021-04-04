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
public class User  implements Serializable {

	private static final long serialVersionUID =  5919385488207520829L;

	private Long id;

	@NotNull(message = "姓名不能为空")
	private String username;

	private String wxOpenId;

	private String phone;

}
