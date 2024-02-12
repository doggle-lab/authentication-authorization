package dogglezz.auth.domain;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OauthClientFinder {
    private final List<OauthClient> oauthClients;

    public OauthClientFinder(List<OauthClient> oauthClients) {
        this.oauthClients = oauthClients;
    }

    public OauthClient find(SocialType socialType) {
        return oauthClients.stream()
                .filter(it -> it.getSocialType() == socialType)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("지원하지 않은 social 로그인 입니다."));
    }
}
