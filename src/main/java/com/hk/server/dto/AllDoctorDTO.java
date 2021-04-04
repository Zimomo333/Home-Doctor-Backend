package com.hk.server.dto;

import com.hk.server.entity.Doctor;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @program: gg2020
 * @description: 医生列表DTO
 * @author: 头微凉
 * @create: 2020-09-18 10:25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class AllDoctorDTO {
    @ApiModelProperty(value = "状态码", example = "状态码：200 or 460")
    int code;
    @ApiModelProperty(value = "状态短语", example = "状态短语：okk or fail")
    String message;
    @ApiModelProperty(value = "医生列表")
    List<Doctor> result;
    @ApiModelProperty("总医生人数")
    long count;
}