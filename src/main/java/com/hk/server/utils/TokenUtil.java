package com.hk.server.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.hk.server.entity.Admin;

import java.util.Date;

public class TokenUtil {

    private static final long EXPIRE_TIME = 10 * 60 * 60 * 1000;
    private static final String TOKEN_SECRET = "txdy";  //密钥盐

    public static String sign(Admin admin) {
        String token = null;
        try {
            Date expiresAt = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            token = JWT.create()
                    .withIssuer("auth0")
                    .withClaim("account", admin.getAccount())
                    .withExpiresAt(expiresAt)
                    // 使用了HMAC256加密算法。
                    .sign(Algorithm.HMAC256(TOKEN_SECRET));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }

    public static boolean verify(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).withIssuer("auth0").build();
            DecodedJWT jwt = verifier.verify(token);
            System.out.println("认证通过：");
            System.out.println("account: " + jwt.getClaim("account").asString());
            System.out.println("过期时间：      " + jwt.getExpiresAt());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Description: 根据token值返回用户id字符串
     *
     * @param token:客户端携带的token值
     * @return java.lang.String
     * @author 头微凉
     * 创建时间: 2020/9/25
     */
    public static String getAccount(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).withIssuer("auth0").build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaim("account").asString();
    }
}
