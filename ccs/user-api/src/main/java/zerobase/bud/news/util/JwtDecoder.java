package zerobase.bud.news.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

import java.util.Base64;


public class JwtDecoder {
    public Long decodeJwtAndGetUserId(String jwtToken){

        String secret = "ZGF5b25lLXNwcmluZy1ib290LWRpdmlkZW5kLXByb2plY3QtdHV0b3JpYWwtand0LXNlY3JldC1rZXkKZGF5b25lLXNwcmluZy1ib290LWRpdmlkZW5kLXByb2plY3QtdHV0b3JpYWwtand0LXNlY3JldC1rZXkK";
        // Base64로 인코딩된 시크릿 키를 디코딩
        byte[] decodedSecret = Base64.getDecoder().decode(secret);

        // JWT 디코딩
        Jws<Claims> claims = Jwts.parser()
                .setSigningKey(decodedSecret) // 디코딩된 시크릿 키 사용
                .parseClaimsJws(jwtToken);

        // 디코딩된 클레임에서 userId 추출
        Long userId = Long.parseLong(claims.getBody().get("user_id").toString());

        // 디코딩된 userId 반환
        return userId;
    }
}