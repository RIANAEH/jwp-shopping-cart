package woowacourse.auth.application;

import org.springframework.stereotype.Service;
import woowacourse.auth.dto.TokenRequest;
import woowacourse.auth.dto.TokenResponse;
import woowacourse.auth.support.JwtTokenProvider;

@Service
public class AuthService {

    private final JwtTokenProvider jwtTokenProvider;

    public AuthService(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public TokenResponse createToken(final TokenRequest tokenRequest) {
        final String token = jwtTokenProvider.createToken(tokenRequest.getUserName());
        return new TokenResponse(token);
    }

    public boolean isInvalidToken(String token) {
        return !jwtTokenProvider.validateToken(token);
    }

    public String getNameFromToken(String token) {
        return jwtTokenProvider.getPayload(token);
    }
}
