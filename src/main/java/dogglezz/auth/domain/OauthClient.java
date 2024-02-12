package dogglezz.auth.domain;

public interface OauthClient {
    OauthToken getToken(String code);

    OauthMember getOauthMember(String accessToken);

    SocialType getSocialType();
}
