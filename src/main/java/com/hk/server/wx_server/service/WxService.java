package com.hk.server.wx_server.service;

import com.hk.server.entity.User;
import com.hk.server.wx_server.dao.UserMapper;
import com.hk.server.wx_server.entity.WxContainer;
import com.hk.server.wx_server.entity.WxNode;
import com.hk.server.wx_server.entity.WxTransmit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Service
public class WxService {


    @Autowired
    private UserMapper userMapper;

//    // appid
//    public static final String WX_LOGIN_APPID = "wx8a2a351d0950cbd1";
//    // appsecret
//    public static final String WX_LOGIN_SECRET = "34ea07671b41bbf81552416f6dc5aefd";

        //梓豪
    // appid
    public static final String WX_LOGIN_APPID = "wx3e5955768b640056";
    // appsecret
    public static final String WX_LOGIN_SECRET = "02878d1ff0518b5893a35c4227fce5e0";

    private static final String TEMPLATE_ID = "BVdsgAaSGBIdxJp3gsklODSO9JKUHKm4QwmsFmhnELE";

    private static final String TIP = "您的电话订单已经完成";

    private static final String COMMUNITY = "家有医生通知";


    /**
     * 开放接口
     * @param id
     */
    public void publishMessage(Long id){

        String openid = userMapper.getOpenIdByUserId(id);
        System.out.println(openid);
        SimpleDateFormat f = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        String time = f.format(now);
        sendAModel(getAccessToken(),openid,time);

    }

    /**
     * 获取小程序的access_token
     * @return
     */
    private String getAccessToken(){
        UriComponents uriComponents = UriComponentsBuilder.fromUriString("https://api.weixin.qq.com/cgi-bin/token?" +
                "grant_type=client_credential&appid="+WX_LOGIN_APPID+"&secret="+WX_LOGIN_SECRET)
                .build();

        RequestEntity<Void> requestEntity = RequestEntity
                .get(uriComponents.toUri())
                .headers(httpHeaders -> httpHeaders.add("User-Agent", "Mozilla/5.0"))
                .build();
        ResponseEntity<String> exchange = new RestTemplate().exchange(requestEntity, String.class);

        JacksonJsonParser jsonParser = new JacksonJsonParser();
        Map<String, Object> stringObjectMap = jsonParser.parseMap(exchange.getBody());
        String accessToken = (String) stringObjectMap.get("access_token");
        return accessToken;
    }

    /**
     * 发送小程序消息推送模板
     * @param token
     * @param openid
     */
    private Map<String, Object> sendAModel(String token,String openid,String date) {

        WxNode node1 = WxNode.builder().value(COMMUNITY).build();
        WxNode node2 = WxNode.builder().value(TIP).build();
        WxNode node3 = WxNode.builder().value(date).build();

        WxContainer wxContainer = WxContainer.builder().name2(node1).thing3(node2).date4(node3).build();

        WxTransmit wxTransmit = WxTransmit.builder().template_id(TEMPLATE_ID).touser(openid).data(wxContainer).build();

        String url = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token="+token;
        HttpHeaders headers = new HttpHeaders();
        headers.add("User-Agent", "Mozilla/5.0");
        HttpMethod method = HttpMethod.POST;
        HttpEntity<WxTransmit> requestEntity = new HttpEntity<>(wxTransmit, headers);
        ResponseEntity<String> exchange = new RestTemplate().exchange(url,method,requestEntity, String.class);
        JacksonJsonParser jsonParser = new JacksonJsonParser();
        Map<String, Object> stringObjectMap = jsonParser.parseMap(exchange.getBody());
        return stringObjectMap;

    }


}
