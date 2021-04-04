package com.hk.server.wx_server.service;

import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    private static final String ARTICLE_KEY_PREFIX = "article:";

    @Autowired
    private RedisTemplate redisTemplate;


    /**
     * 为文章观看数+1
     * @param id
     */
    public void increArticleView(String id){
        redisTemplate.opsForValue().increment(ARTICLE_KEY_PREFIX + "see:" + id);
    }

    /**
     * 获取文章观看数
     * @param id
     * @return
     */
    public Integer getArticleView(String id){
        Integer number = (Integer) redisTemplate.opsForValue().get(ARTICLE_KEY_PREFIX + "see:" + id);
        return number;
    }

    /**
     * 删除文章观看对应的redis键
     * @param id
     */
    public void deleteArticleView(String id){
        Boolean result = redisTemplate.delete(ARTICLE_KEY_PREFIX + "see:" + id);
    }

    /**
     * 为文章点赞数+1
     * @param id
     */
    public void increArticleLikes(String id){
        redisTemplate.opsForValue().increment(ARTICLE_KEY_PREFIX + "like:" + id);
    }

    /**
     * 获取文章点赞数
     * @param id
     * @return
     */
    public Integer getArticleLikes(String id){
        Integer number = (Integer) redisTemplate.opsForValue().get(ARTICLE_KEY_PREFIX + "like:" + id);
        return number;
    }

    /**
     * 删除文章点赞对应的redis键
     * @param id
     */
    public void deleteArticleLikes(String id){
        Boolean result = redisTemplate.delete(ARTICLE_KEY_PREFIX + "like:" + id);
    }

}
