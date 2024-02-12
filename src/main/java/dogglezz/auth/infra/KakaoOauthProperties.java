package dogglezz.auth.infra;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("oauth2.user.kakao")
public record KakaoOauthProperties(
        String clientId,
        String clientSecret,
        String redirectUri,
        String accessTokenUrl,
        String userInfoUrl
) {
}
