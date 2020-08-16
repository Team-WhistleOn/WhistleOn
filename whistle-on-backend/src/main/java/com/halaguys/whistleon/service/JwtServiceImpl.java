package com.halaguys.whistleon.service;

import com.halaguys.whistleon.domain.user.User;

import com.halaguys.whistleon.exception.UnauthorizedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static java.nio.charset.StandardCharsets.UTF_8;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Date;

@Service
@AllArgsConstructor
public class JwtServiceImpl implements JwtService {
    private static final String SALT = "halaGuysWhistleOn";

    @Override
    public String create(User subject){
        String jwt = Jwts.builder()
                .setHeaderParam("typ","JWT")
                .setHeaderParam("regDate",System.currentTimeMillis())
                .setSubject(subject.getEmail())
                .claim("userId",subject.getUserId())
                .claim("email",subject.getEmail())
                .claim("userName",subject.getUserName())
                //.claim("userId",subject....
                .setExpiration(new Date(System.currentTimeMillis()+(1000*60*60)))
                .signWith(SignatureAlgorithm.HS256, this.generateKey())
                .compact(); //직렬화
        return jwt;
    }

    @Override
    public boolean isUsable(String jwt) throws UnauthorizedException{
        try {
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(this.generateKey())
                    .parseClaimsJws(jwt);
            return true;
        }catch (Exception e){
            throw new UnauthorizedException();
        }
    }

    private byte[] generateKey(){
        byte[] key = null;
        key = SALT.getBytes(UTF_8);
        return key;
    }

    @Override
    //jwt에 있는 데이터 가져오고 싶을 때,
    public Object get(String key) throws Exception {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String jwt = request.getHeader("Authorization");
        Jws<Claims> claims = null;
        try{
            claims = Jwts.parser()
                    .setSigningKey(SALT.getBytes("UTF-8"))
                    .parseClaimsJws(jwt);
        } catch (UnsupportedEncodingException e) {
            throw new Exception();
        }
        return claims.getBody().get(key);
    }
}