package com.hk.server.wx_server.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.hk.server.entity.Admin;

import java.util.Date;

public class WxTokenUtil {

    private static final long EXPIRE_TIME= 10*60*60*1000;
    private static final String TOKEN_SECRET="txdy";  //密钥盐

    public static String sign(String openid,Long id){
        String token = null;
        try {
            Date expiresAt = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            token = JWT.create()
                    .withIssuer("auth0")
                    .withClaim("openid", openid).withClaim("id",id)
                    .withExpiresAt(expiresAt)
                    // 使用了HMAC256加密算法。
                    .sign(Algorithm.HMAC256(TOKEN_SECRET));
        } catch (Exception e){
            e.printStackTrace();
        }
        return token;
    }

    public static boolean verify(String token){
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).withIssuer("auth0").build();
            DecodedJWT jwt = verifier.verify(token);
            System.out.println("认证通过：");
            System.out.println("openid: " + jwt.getClaim("openid").asString());
            System.out.println("id: " + jwt.getClaim("id").asLong());
            System.out.println("过期时间：      " + jwt.getExpiresAt());
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public static String fromTokenGetOpenid(String token){
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).withIssuer("auth0").build();
            DecodedJWT jwt = verifier.verify(token);
            String s = jwt.getClaim("openid").asString();
            return s;
        } catch (Exception e){
            return null;
        }
    }

    public static Long fromTokenGetId(String token){
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).withIssuer("auth0").build();
            DecodedJWT jwt = verifier.verify(token);
            Long s = jwt.getClaim("id").asLong();
            return s;
        } catch (Exception e){
            return null;
        }
    }

}
