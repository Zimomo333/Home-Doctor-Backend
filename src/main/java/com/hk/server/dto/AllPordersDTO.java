package com.hk.server.dto;

import com.hk.server.entity.Porder;
import com.hk.server.entity.Porder1;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @program: gg2020
 * @description:
 * @author: 头微凉
 * @create: 2020-10-06 15:27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class AllPordersDTO {
    @ApiModelProperty(value = "状态码", example = "状态码：200 or 460")
    int code;
    @ApiModelProperty(value = "状态短语", example = "状态短语：okk or fail")
    String message;
    @ApiModelProperty(value = "电话订单列表")
    List<Porder1> result;
}