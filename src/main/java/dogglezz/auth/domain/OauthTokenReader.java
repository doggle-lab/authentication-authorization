package dogglezz.auth.domain;

import org.springframework.stereotype.Component;

@Component
public class OauthTokenReader {
    private final OauthClientFinder oauthClientFinder;

    public OauthTokenReader(OauthClientFinder oauthClientFinder) {
        this.oauthClientFinder = oauthClientFinder;
    }

    public OauthToken read(String code, SocialType socialType) {
        OauthClient oauthClient = oauthClientFinder.find(socialType);
        return oauthClient.getToken(code);
    }
}
