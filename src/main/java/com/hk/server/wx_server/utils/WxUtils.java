package com.hk.server.wx_server.utils;


import com.hk.server.wx_server.entity.WxContainer;
import com.hk.server.wx_server.entity.WxNode;
import com.hk.server.wx_server.entity.WxTransmit;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Map;

public class WxUtils {

    // 请求的网址
    public static final String WX_LOGIN_URL = "https://api.weixin.qq.com/sns/jscode2session?appid={appid}&secret={secret}&js_code={js_code}&grant_type={grant_type}";
//    // appid
//    public static final String WX_LOGIN_APPID = "wx8a2a351d0950cbd1";
//    // appsecret
//    public static final String WX_LOGIN_SECRET = "34ea07671b41bbf81552416f6dc5aefd";

    //梓豪
    // appid
    public static final String WX_LOGIN_APPID = "wx3e5955768b640056";
    // appsecret
    public static final String WX_LOGIN_SECRET = "02878d1ff0518b5893a35c4227fce5e0";
//
    // 固定参数
    public static final String WX_LOGIN_GRANT_TYPE = "authorization_code";

    /**
     * 根据code得到openid
     * @return
     */
    public static String useCodeToGetOpenId(String code){

        RestTemplate rest = new RestTemplate();

        UriComponents uri = UriComponentsBuilder
                .fromUriString(WX_LOGIN_URL)
                .build();
        URI location = uri.expand(WX_LOGIN_APPID,WX_LOGIN_SECRET,code,WX_LOGIN_GRANT_TYPE).encode().toUri();

        String info = rest.getForObject(location,String.class);

        JacksonJsonParser parser = new JacksonJsonParser();
        Map<String, Object> results = parser.parseMap(info);

        return (String) results.get("openid");

    }


}
