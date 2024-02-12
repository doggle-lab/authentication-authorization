package dogglezz.auth.infra;

import dogglezz.auth.domain.OauthToken;

public record KakaoTokenResponse(
        String accessToken,
        String refreshToken,
        Long expiresIn,
        Long refreshTokenExpiresIn
) {

    public OauthToken toOauthToken() {
        return new OauthToken(
                accessToken,
                refreshToken,
                refreshTokenExpiresIn
        );
    }
}
