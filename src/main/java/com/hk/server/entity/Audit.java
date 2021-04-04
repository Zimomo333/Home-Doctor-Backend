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
public class Audit  implements Serializable {

	private static final long serialVersionUID =  2730438686505322716L;

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

	private String commitTime;

	@NotNull(message = "联系邮箱不能为空")
	private String email;

	public void setCommitTime(String commitTime) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (commitTime.contains("-")) {
			try {
				Date parse = simpleDateFormat.parse(commitTime);
				this.commitTime = String.valueOf(parse.getTime());
			} catch (Exception e) {
				this.commitTime = String.valueOf(new Date().getTime());
			}
		}
		else {
			try {
				this.commitTime = simpleDateFormat.format(new Date(Long.parseLong(commitTime)));
			}catch (Exception e) {
				this.commitTime =simpleDateFormat.format(new Date());
			}
		}
	}

}
