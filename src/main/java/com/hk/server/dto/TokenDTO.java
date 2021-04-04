package com.hk.server.dto;

import com.hk.server.entity.Audit;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class TokenDTO {

    @ApiModelProperty(value = "状态码",example = "状态码：200 or 460")
    int code;
    @ApiModelProperty(value = "状态短语",example = "状态短语：okk or fail")
    String message;
    @ApiModelProperty(value = "token令牌",example = "token令牌")
    String token;

}
