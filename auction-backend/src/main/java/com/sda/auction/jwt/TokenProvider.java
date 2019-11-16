package com.sda.auction.jwt;

import com.sda.auction.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

/**
 * Created by Halip on 16.11.2019.
 */
@Component
public class TokenProvider implements InitializingBean{
    private SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;


    @Value("${jwt.server.secret}")
    private String serverSecret;
    ///cheia trebuie transformata intr-un signingkey
    private Key signingKey;
    @Value("${jwt.role.admin.protected}")
    private String adminProtectedPaths;
    @Value("${jwt.role.admin.protected}")
    private String userProtectedPaths;

    ////aici creez jws-ul in sine
    public String createJwt(User user) {
        return Jwts.builder()
                .claim("email",user.getEmail())
                .claim("role",user.getRoles())
                .signWith(signatureAlgorithm, signingKey).compact();
    }

    @Override
    public void afterPropertiesSet() throws Exception{
        ///aici pun secretul serverului ( din application.propeties)
        byte[] keyBytes = Decoders.BASE64.decode(serverSecret);
        ////imi instantiez cheia ca sa o pot folosi mai apoi in createJwt
        signingKey = new SecretKeySpec(keyBytes, signatureAlgorithm.getJcaName());
    }

}
