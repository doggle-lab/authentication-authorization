package dogglezz.auth.infra;

import dogglezz.auth.domain.OauthClient;
import dogglezz.auth.domain.OauthMember;
import dogglezz.auth.domain.OauthToken;
import dogglezz.auth.domain.SocialType;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

public class KakaoOauthClient implements OauthClient {
    private final KakaoOauthProperties kakaoOauthProperties;
    private final RestClient restClient;

    public KakaoOauthClient(KakaoOauthProperties kakaoOauthProperties, RestClient restClient) {
        this.kakaoOauthProperties = kakaoOauthProperties;
        this.restClient = restClient;
    }

    @Override
    public OauthToken getToken(String code) {
        var result = restClient.post()
                .uri(kakaoOauthProperties.accessTokenUrl(), it -> {
                    return it.queryParam("client_id", kakaoOauthProperties.clientId())
                            .queryParam("redirect_uri", kakaoOauthProperties.redirectUri())
                            .queryParam("code", code)
                            .queryParam("client_secret", kakaoOauthProperties.clientSecret())
                            .build();

                })
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .accept(MediaType.APPLICATION_JSON)

                .exchange((request, response) -> {
                    if (response.getStatusCode().is4xxClientError() || response.getStatusCode().is5xxServerError()) {
                        throw new IllegalArgumentException("failed Social Login");
                    }
                    KakaoTokenResponse kakaoTokenResponse = response.bodyTo(KakaoTokenResponse.class);
                    if (kakaoTokenResponse == null) {
                        throw new IllegalArgumentException("failed Social Login");
                    }
                    return kakaoTokenResponse;
                });

        return result.toOauthToken();
    }

    @Override
    public OauthMember getOauthMember(String accessToken) {
        var result = restClient.get()
                .uri(kakaoOauthProperties.userInfoUrl())
                .header(HttpHeaders.AUTHORIZATION, "Bearer %s".formatted(accessToken))
                .exchange((request, response) -> {
                    if (response.getStatusCode().is4xxClientError() || response.getStatusCode().is5xxServerError()) {
                        throw new IllegalArgumentException("failed Social Login");
                    }
                    var kakaoUserInfo = response.bodyTo(KakaoUserInfo.class);
                    if (kakaoUserInfo == null) {
                        throw new IllegalArgumentException("failed Social Login");
                    }
                    return kakaoUserInfo;
                });

        return result.toOauthMember();
    }

    @Override
    public SocialType getSocialType() {
        return SocialType.KAKAO;
    }
}
