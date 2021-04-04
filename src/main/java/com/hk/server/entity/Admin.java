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
public class Admin  implements Serializable {

	private static final long serialVersionUID =  100721320456714316L;

	private Long id;

	@NotNull(message = "账号不能为空")
	private String account;

	@NotNull(message = "密码不能为空")
	private String password;

}
