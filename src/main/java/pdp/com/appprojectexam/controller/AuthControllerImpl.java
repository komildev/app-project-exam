package pdp.com.appprojectexam.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import pdp.com.appprojectexam.controller.AuthController;
import pdp.com.appprojectexam.exception.ApiResult;
import pdp.com.appprojectexam.payload.*;
import pdp.com.appprojectexam.service.AuthService;

@RestController
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {
    private final AuthService authService;

    @Override
    public ApiResult<?> signUp(RegisterDTO registerDTO) {
        return authService.signUp(registerDTO);
    }

    @Override
    public ApiResult<TokenDTO> signIn(SignInDTO signInDTO) {
        return authService.signIn(signInDTO);
    }

    @Override
    public ApiResult<?> confirmAccount(Integer userId, String verificationCode) {
        return authService.confirmAccount(userId,verificationCode);
    }


}
