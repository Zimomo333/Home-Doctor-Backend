package com.hk.server.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "错误对象")
public class FailDTO {

    @ApiModelProperty(value="状态",example = "460")
    int code;
    @ApiModelProperty(value="状态",example = "fail")
    String message;

}
