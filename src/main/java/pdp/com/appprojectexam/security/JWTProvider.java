package pdp.com.appprojectexam.security;


import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pdp.com.appprojectexam.exception.RestException;
import pdp.com.appprojectexam.exception.TokenExpiredException;
import pdp.com.appprojectexam.utils.AppConstant;

import java.sql.Timestamp;
import java.util.Date;
@Service
public class JWTProvider {
    @Value("${jwt.access.secret}")
    private String accessKey;
    @Value("${jwt.refresh.secret}")
    private String refreshKey;
    private final long accessTokenlifeTime = 24 * 60 * 60 * 1000;
    private final long refreshTokenlifeTime = 7 * 24 * 60 * 60 * 1000;

    public String generateAccessToken(String username) {

        String token = Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(new Date())//BIRTHDATE
                .setExpiration(new Date(System.currentTimeMillis() + accessTokenlifeTime))//DEAD DATE
                .signWith(SignatureAlgorithm.HS512, accessKey)
                .compact();

        return AppConstant.BEARER_TOKEN + token;
    }

    public String generateRefreshToken(String username) {

        String token = Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(new Date())//BIRTHDATE
                .setExpiration(new Date(System.currentTimeMillis() + refreshTokenlifeTime))//DEAD DATE
                .signWith(SignatureAlgorithm.HS512, refreshKey)
                .compact();

        return AppConstant.BEARER_TOKEN + token;
    }

    public static void main(String[] args) {
        Timestamp timestamp= new Timestamp(System.currentTimeMillis());
        System.out.println(timestamp);
    }

    public String getUsernameFromToken(String token) {
        try {
            token = token.substring(AppConstant.BEARER_TOKEN.length());
            String username = Jwts
                    .parser()
                    .setSigningKey(accessKey)
                 //   .setSigningKey(refreshKey)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();

            return username;

        }catch (ExpiredJwtException ex){
            ex.printStackTrace();
            throw new TokenExpiredException();
        } catch (Exception e) {
            e.printStackTrace();
            throw RestException.forbidden();
        }
    }


}
