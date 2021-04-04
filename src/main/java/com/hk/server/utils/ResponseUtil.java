package com.hk.server.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseUtil {

    int code; //响应码
    String attach; //附加短语
    Object data; //响应数据

}
