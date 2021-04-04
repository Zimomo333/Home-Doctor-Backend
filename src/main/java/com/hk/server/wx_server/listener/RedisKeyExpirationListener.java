package com.hk.server.wx_server.listener;

import com.hk.server.entity.Morder;
import com.hk.server.wx_server.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

@Component
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    UserMapper userMapper;

    public RedisKeyExpirationListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        RedisSerializer<String> serializer = redisTemplate.getValueSerializer();
        // 获取到失效的 key
        String key = message.toString();

        //如果是图文订单的key值
        if (key.startsWith("morder:")){
            //获取到订单id
            Long id = Long.valueOf(key.substring(7));
            //得到订单信息
            Morder morder = userMapper.findMorder(id);
            //判断订单是否未确认
            if (morder.getUserConfirm()==0){
                SimpleDateFormat f = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
                Date now = new Date();
                String ctime = f.format(now);
                //更新订单为确认状态
                userMapper.confirmMorder(id, ctime);
                //更新医生的服务数
                userMapper.increDoctorServices(morder.getDoctorid());
            }
        }

    }
}
