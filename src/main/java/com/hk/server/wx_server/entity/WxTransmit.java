package com.hk.server.wx_server.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WxTransmit {

    String template_id;
    String touser;
    WxContainer data;

}
