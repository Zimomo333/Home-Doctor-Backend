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
public class Doctor  implements Serializable {

	private static final long serialVersionUID =  7526785885226899425L;

	private Long id;

	@NotNull(message = "性别不能为空")
	private Integer sex;

	@NotNull(message = "姓名不能为空")
	private String realName;

	@NotNull(message = "省份不能为空")
	private String province;

	@NotNull(message = "市区不能为空")
	private String area;

	private String address;

	private String message;

	private String background;

	@NotNull(message = "医院不能为空")
	private String hospital;

	@NotNull(message = "科室不能为空")
	private String department;

	@NotNull(message = "副科室不能为空")
	private String deputyDeparment;

	@NotNull(message = "职称不能为空")
	private String professor;

	@NotNull(message = "擅长不能为空")
	private String skills;

	@NotNull(message = "图文咨询期望价格不能为空")
	@Min(value = 0,message = "价格需为正数")
	private Double morderPrice;

	@NotNull(message = "电话咨询期望价格不能为空")
	@Min(value = 0,message = "价格需为正数")
	private Double porderPrice;

	private Long services;

	private Long allcommonts;

	private Long goodcommonts;

	private Double balance;

	private Long state;

	@NotNull(message = "联系邮箱不能为空")
	private String email;

	@NotNull(message = "密码不能为空")
	private String password;

}
