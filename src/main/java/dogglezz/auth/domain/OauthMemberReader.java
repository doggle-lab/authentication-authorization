package dogglezz.auth.domain;

import org.springframework.stereotype.Component;

@Component
public class OauthMemberReader {
    private final OauthClientFinder oauthClientFinder;

    public OauthMemberReader(OauthClientFinder oauthClientFinder) {
        this.oauthClientFinder = oauthClientFinder;
    }

    public OauthMember find(String accessToken, SocialType socialType) {
        OauthClient oauthClient = oauthClientFinder.find(socialType);
        return oauthClient.getOauthMember(accessToken);
    }
}
