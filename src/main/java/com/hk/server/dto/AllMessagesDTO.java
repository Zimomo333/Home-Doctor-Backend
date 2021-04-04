package com.hk.server.dto;

import com.hk.server.entity.Doctor;
import com.hk.server.entity.MorderMessages;
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
 * @create: 2020-10-10 19:32
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class AllMessagesDTO {
    @ApiModelProperty(value = "状态码", example = "状态码：200 or 460")
    int code;
    @ApiModelProperty(value = "状态短语", example = "状态短语：okk or fail")
    String message;
    @ApiModelProperty(value = "消息列表")
    List<MorderMessages> result;
}