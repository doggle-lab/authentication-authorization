package dogglezz.auth.domain;

public record OauthToken(
        String accessToken,
        String refreshToken,
        Long refreshTokenExpiredIn
) {
}
