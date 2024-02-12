package dogglezz.auth.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByOauthIdAndSocialType(String oauthId, SocialType socialType);
}
