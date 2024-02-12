package dogglezz.auth.application;

import dogglezz.auth.domain.JwtTokenProvider;
import dogglezz.auth.domain.Member;
import dogglezz.auth.domain.MemberService;
import dogglezz.auth.domain.OauthMember;
import dogglezz.auth.domain.OauthMemberReader;
import dogglezz.auth.domain.OauthToken;
import dogglezz.auth.domain.OauthTokenReader;
import dogglezz.auth.domain.SocialType;
import org.springframework.stereotype.Service;

@Service
public class SocialLoginUseCase {
    private final MemberService memberService;
    private final OauthTokenReader oauthTokenReader;
    private final OauthMemberReader oauthMemberReader;
    private final JwtTokenProvider jwtTokenProvider;

    public SocialLoginUseCase(MemberService memberService,
                              OauthTokenReader oauthTokenReader,
                              OauthMemberReader oauthMemberReader,
                              JwtTokenProvider jwtTokenProvider) {
        this.memberService = memberService;
        this.oauthTokenReader = oauthTokenReader;
        this.oauthMemberReader = oauthMemberReader;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public Result login(String code, String type) {
        var socialType = SocialType.getSocialType(type);
        OauthToken oauthToken = oauthTokenReader.read(code, socialType);
        OauthMember oauthMember = oauthMemberReader.find(oauthToken.accessToken(), socialType);
        Member member = memberService.findOrSignup(oauthMember.toMember());
        String token = jwtTokenProvider.createToken(member.getId());
        return new Result(token);
    }


    public record Result(
            String accessToken
    ) {
    }
}
