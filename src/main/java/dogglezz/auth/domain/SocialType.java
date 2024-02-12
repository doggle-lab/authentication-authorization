package dogglezz.auth.domain;

import java.util.stream.Stream;

public enum SocialType {
    KAKAO;

    public static SocialType getSocialType(String type) {
        return Stream.of(values())
                .filter(it -> it.name().equalsIgnoreCase(type))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("지원하지 않은 social 로그인 입니다."));
    }
}
