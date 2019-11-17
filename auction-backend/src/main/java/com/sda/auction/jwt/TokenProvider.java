package com.sda.auction.jwt;

import com.sda.auction.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.ArrayList;
import java.util.Optional;

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
    @Value("${jwt.role.user.protected}")
    private String userProtectedPaths;

    ////aici creez jws-ul in sine
    public String createJwt(User user) {
        return Jwts.builder()
                .claim("email",user.getEmail())
                .claim("role",user.getRolesAsString())
                .signWith(signatureAlgorithm, signingKey).compact();
    }

    @Override
    public void afterPropertiesSet() throws Exception{
        ///aici pun secretul serverului ( din application.propeties)
        /// decodez in base 64 secretul meu de server
        byte[] keyBytes = Decoders.BASE64.decode(serverSecret);
        ////imi instantiez cheia ca sa o pot folosi mai apoi in createJwt
        signingKey = new SecretKeySpec(keyBytes, signatureAlgorithm.getJcaName());
    }

    public boolean validate(String jwt, String requestURL) {

        ///in caz ca jwt este null
        if(requestURL.compareTo("/api/login") ==0 ||
                requestURL.compareTo("/api/register")== 0){
            return true;
        }
        ////imi ia semnatura si o verifica si imi returneza jwt ul cu getBody()
        ///in claims avem : email, role si jwt
        Optional<Claims> optinalClaims = decodeJwt(jwt);
        Claims claims = optinalClaims.get();
        if(!optinalClaims.isPresent()){
            return false;
        }
        ////urmeaza verificarea autorizarii
        String[] adminProtected = adminProtectedPaths.split(",");
        for(String path: adminProtected){
            if(requestURL.contains(path)){
                ArrayList<String> rolesName = claims.get("roles", ArrayList.class);
                for(String roleName :rolesName){
                    if(roleName.compareTo("admin")==0){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private Optional<Claims> decodeJwt(String jwt) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(signingKey)
                    .parseClaimsJws(jwt)
                    .getBody();
            return Optional.of(claims);
        }catch (Exception e){
            return Optional.empty();
        }
    }
}
