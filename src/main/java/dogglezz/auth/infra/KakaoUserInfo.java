package dogglezz.auth.infra;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import dogglezz.auth.domain.OauthMember;
import dogglezz.auth.domain.SocialType;

public record KakaoUserInfo(
        String id,
        KakaoAccount kakaoAccount,
        Properties properties,
        String email
) {

    public OauthMember toOauthMember() {
        return new OauthMember(
                id,
                properties().nickname(),
                kakaoAccount.profile().profileImageUrl(),
                email,
                SocialType.KAKAO
        );
    }

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    record KakaoAccount(
            String name,
            Profile profile
    ) {
    }

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    record Profile(
            String profileImageUrl
    ) {
    }

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    record Properties(
            String nickname
    ) {
    }
}
