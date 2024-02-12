package dogglezz.auth.api;

import dogglezz.auth.application.SocialLoginUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginApi {
    private final SocialLoginUseCase socialLoginUseCase;

    public LoginApi(SocialLoginUseCase socialLoginUseCase) {
        this.socialLoginUseCase = socialLoginUseCase;
    }

    @GetMapping("/oauth/{type}")
    public ResponseEntity<TokenResponse> login(
            @RequestParam(name = "code") String code,
            @PathVariable(name = "type") String type
    ) {
        var response = socialLoginUseCase.login(code, type);
        return ResponseEntity.ok().body( new TokenResponse(response.accessToken()));
    }

    record TokenResponse(String accessToken) {
    }
}
