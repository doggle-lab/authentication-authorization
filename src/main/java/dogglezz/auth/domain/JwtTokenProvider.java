package dogglezz.auth.domain;

import dogglezz.auth.infra.JwtTokenProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {
    private final JwtTokenProperties jwtTokenProperties;

    private final Key key;

    public JwtTokenProvider(JwtTokenProperties jwtTokenProperties) {
        this.jwtTokenProperties = jwtTokenProperties;
        key = Keys.hmacShaKeyFor(jwtTokenProperties.key().getBytes(StandardCharsets.UTF_8));
    }

    public String createToken(Long id) {
        var now = new Date();
        var expiration = new Date(now.getTime() + jwtTokenProperties.token().expired());
        return Jwts.builder()
                .setSubject(id.toString())
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public Long getSubject(String token) {
        return Long.valueOf(getClaims(token).getSubject());
    }


    public boolean verityToken(String token) {
        try {
            return !getClaims(token).getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    private Claims getClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }

}