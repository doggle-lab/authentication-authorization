package dogglezz.auth.infra;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "jwt.secret")
public record JwtTokenProperties(
        String key,
        Token token
) {

    public record Token(
            Long expired
    ) {
    }
}
