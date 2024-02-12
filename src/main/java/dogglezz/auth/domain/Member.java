package dogglezz.auth.domain;

import dogglezz.auth.support.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;

@Entity
@Table(
        indexes = {
                @Index(name = "idx_member_oauth_id", columnList = "oauth_id")
        }
)
public class Member extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, name = "oauth_id")
    private String oauthId;
    private String username;
    private String profileUrl;
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private SocialType SocialType;

    protected Member() {
    }

    public Member(String oauthId, String username, String profileUrl, String email, SocialType SocialType) {
        this.oauthId = oauthId;
        this.username = username;
        this.profileUrl = profileUrl;
        this.email = email;
        this.role = Role.MEMBER;
        this.SocialType = SocialType;
    }

    public Long getId() {
        return id;
    }

    public String getOauthId() {
        return oauthId;
    }

    public String getUsername() {
        return username;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }

    public SocialType getSocialType() {
        return SocialType;
    }
}
