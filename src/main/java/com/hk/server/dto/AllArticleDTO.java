package com.hk.server.dto;

import com.hk.server.entity.Healthyabout;
import com.hk.server.entity.Healthyabout1;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @program: gg2020
 * @description: 文章类DTO
 * @author: 头微凉
 * @create: 2020-10-05 11:23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class AllArticleDTO {
    @ApiModelProperty(value = "状态码", example = "状态码：200 or 460")
    int code;
    @ApiModelProperty(value = "状态短语", example = "状态短语：okk or fail")
    String message;
    @ApiModelProperty(value = "文章列表")
    List<Healthyabout1> result;
}