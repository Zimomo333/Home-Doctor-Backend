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
public class Healthrecord  implements Serializable {

	private static final long serialVersionUID =  5724934226348929967L;

	private Long id;

	private Long userid;

	private String username;

	private Integer sex;

	private String birthdate;

	private Double height;

	private Integer issmoke;

	private Integer isdrink;

	private Integer liverState;

	private Integer ismarry;

	private String chronicDisease;

	private String other;

	private String allergy;

	private String historyDisease;

	private Double weight;

	public void setBirthdate(String birthdate) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (birthdate.contains("-")) {
			try {
				Date parse = simpleDateFormat.parse(birthdate);
				this.birthdate = String.valueOf(parse.getTime());
			} catch (Exception e) {
				this.birthdate = String.valueOf(new Date().getTime());
			}
		}
		else {
			try {
				this.birthdate = simpleDateFormat.format(new Date(Long.parseLong(birthdate)));
			}catch (Exception e) {
				this.birthdate =simpleDateFormat.format(new Date());
			}
		}
	}

}
