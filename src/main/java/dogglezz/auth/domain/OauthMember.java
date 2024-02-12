package dogglezz.auth.domain;

public record OauthMember(
        String oauthId,
        String username,
        String profileUrl,
        String email,
        SocialType type
) {

    public Member toMember() {
        return new Member(
                oauthId,
                username,
                profileUrl,
                email,
                type
        );
    }
}
