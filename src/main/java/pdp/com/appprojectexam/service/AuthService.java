package pdp.com.appprojectexam.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import pdp.com.appprojectexam.exception.ApiResult;
import pdp.com.appprojectexam.payload.*;

public interface AuthService extends UserDetailsService {
    ApiResult<?> signUp(RegisterDTO registerDTO);
    ApiResult<TokenDTO> signIn(SignInDTO signInDTO);
    ApiResult<?> confirmAccount(Integer userId, String verificationCode);

    //ApiResult<?> refreshToken(RefreshTokenRequest request);


}
