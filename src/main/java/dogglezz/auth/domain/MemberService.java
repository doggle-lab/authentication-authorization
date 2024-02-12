package dogglezz.auth.domain;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public Member findOrSignup(Member member) {
        return memberRepository.findByOauthIdAndSocialType(member.getOauthId(), member.getSocialType())
                .orElseGet(() -> memberRepository.save(member));
    }

    @Transactional(readOnly = true)
    public Member getById(Long id) {
        return  memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("이용하실 수 없습니다"));
    }
}
