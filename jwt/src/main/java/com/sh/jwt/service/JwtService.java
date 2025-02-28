package com.sh.jwt.service;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

@Slf4j
@Service
public class JwtService {

    private static String secretKey = "java11SpringBootJWTTokenIssueMethod";

    //token 생성
    public String create(
            Map<String, Object> claims,
            LocalDateTime expireAt
    ){

        //custom key
        var key = Keys.hmacShaKeyFor(secretKey.getBytes());

        //LocalDateTime -> Date 형변환
        var _expiredAt = Date.from(expireAt.atZone(ZoneId.systemDefault()).toInstant());
        return Jwts.builder()
                .signWith(key, SignatureAlgorithm.HS256) //HS256 알고리즘 사용
                .setClaims(claims)
                .setExpiration(_expiredAt)
                .compact(); //생성한 jwt를 string type으로 return 해줌

    }

    //token 검증
    public void validation(String token){
        var key = Keys.hmacShaKeyFor(secretKey.getBytes());

        var parser = Jwts.parserBuilder().setSigningKey(key).build();

        try{
            var result = parser.parseClaimsJws(token);
            result.getBody().entrySet().forEach(value -> {
                log.info("key: {}, value: {}", value.getKey(), value.getValue());
            });

        }catch(Exception e){
            if(e instanceof SignatureException){
                throw new RuntimeException("JWT Token Not Valid Exception");
            }
            else if(e instanceof ExpiredJwtException){
                throw new RuntimeException("JWT Token Expires Exception");
            }else{
                throw new RuntimeException("JWT Token Validation Exception");
            }
        }





    }
}
