package com.demo.myshiro.util;


import com.demo.myshiro.constant.Constant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.thymeleaf.util.StringUtils;
import javax.xml.bind.DatatypeConverter;
import java.time.Duration;
import java.util.Date;
import java.util.Map;

//服务器无法废弃令牌
//
//        JWT的最大缺点是服务器不保存会话状态，所以在使用期间不可能取消令牌或更改令牌的权限。也就是说，一旦JWT签发，在有效期内将会一直有效。
//
//        这个问题也有相应的解决方案，但是要借助于缓存中间件，每次JWT生成一个token的同时，也生成一个refreshToken(UUID方式生成即可)存储在
//        Redis等缓存中间件中，并将生成时间、refreshToken封装在jwt中。若用户退出登录就将Redis中对应的refreshToken清除，若用户更新jwt就将
//        Redis中refreshToken的生成时间更新。每次客户端验证时，校验jwt中的refreshToken生成时间和Redis中存储的是否一致，如果不一致，证明
//        此用户的jwt已被更新过，不予通过。
//
//        用上面的解决方案也可以让服务端有强制登录者下线的能力，只要将jwt有效时间设置短一些，refreshToken有效时间长一些，当客户端请求服务器
//        时，若判断jwt过期，从Redis中查询refreshToken是否在有效时间内，有效的话，重新生成一个jwtToken给客户端。这样的话，当服务器检测到用
//        户登录地异常或者其他异常情况发生，便可以删掉Redis中存储当前用户的refreshToken，强制其下线。
//
//        其实出于信息安全的考虑，jwt的有效时间不宜设置过长，尽量在一下重要操作里每次都进行身份验证。
public class JwtTokenUtil {

    public static String secretKey;

    public static Duration accessTokenExpireTime;

    public static Duration refreshTokenExpireTime;

    public static Duration refreshTokenExpireAppTime;

    public static String issuer;

    public static void setProperty(TokenSetting tokenSetting){
        secretKey = tokenSetting.getSecretKey();
        accessTokenExpireTime = tokenSetting.getAccessTokenExpireTime();
        refreshTokenExpireAppTime = tokenSetting.getRefreshTokenExpireAppTime();
        refreshTokenExpireTime = tokenSetting.getRefreshTokenExpireTime();
        issuer = tokenSetting.getIssuer();
    }
    public static String getAccessToken(String subject,Map<String,Object> claims){
        return generateToken(issuer,subject,claims,accessTokenExpireTime.toMillis(),secretKey);
    }
    public static String getRefreshToken(String subject,Map<String,Object> claims){
        return generateToken(issuer,subject,claims,refreshTokenExpireTime.toMillis(),secretKey);
    }
    public static String getRefreshAppToken(String subject,Map<String,Object> claims){
        return generateToken(issuer,subject,claims,refreshTokenExpireAppTime.toMillis(),secretKey);
    }
    public static Claims getClaimsFromToken(String token){
        Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(secretKey)).parseClaimsJws(token).getBody();
        return claims;
    }
    public static String getUserId(String token){
        Claims claims = getClaimsFromToken(token);
        String subject = claims.getSubject();
        return subject;
    }
    public static String getUsername(String token){
        Claims claims = getClaimsFromToken(token);
        String username = (String) claims.get(Constant.JWT_USER_NAME);
        return username;
    }
    public static boolean isTokenExpired(String token){
        Claims claims = getClaimsFromToken(token);
        Date expiration = claims.getExpiration();
        long expirationTime = expiration.getTime();
        return expirationTime < System.currentTimeMillis() ? true : false;
    }
    public static boolean validateToken(String token){
        Claims claimsFromToken = getClaimsFromToken(token);
        return ( null != claimsFromToken && !isTokenExpired(token) ? true : false);
    }
    public static long getRemainingTime(String token){
        if(validateToken(token)){
            Claims claimsFromToken = getClaimsFromToken(token);
            long remainingTime = claimsFromToken.getExpiration().getTime() - System.currentTimeMillis();
            return remainingTime;
        }
        return 0;
    }
    public static String generateToken(String issuer,String subject, Map<String,Object> claims,long ttlMills,String secret){
        JwtBuilder builder = Jwts.builder();
        builder.setHeaderParam("typ","jwt");
        long currentTimeMillis = System.currentTimeMillis();
        builder.setIssuedAt(new Date(currentTimeMillis));

        if(null != claims){
            builder.setClaims(claims);
        }
        if(!StringUtils.isEmpty(issuer)){
            builder.setIssuer(issuer);
        }
        if(ttlMills > 0){
            builder.setExpiration(new Date(currentTimeMillis+ ttlMills));
        }
        if(!StringUtils.isEmpty(subject)){
            builder.setSubject(subject);
        }

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] bytes = DatatypeConverter.parseBase64Binary(secret);
        builder.signWith(signatureAlgorithm,bytes);
        return builder.compact();
    }


}
