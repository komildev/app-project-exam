package pdp.com.appprojectexam.controller;

import org.springframework.web.bind.annotation.*;
import pdp.com.appprojectexam.exception.ApiResult;
import pdp.com.appprojectexam.payload.*;
import pdp.com.appprojectexam.utils.AppConstant;

@RequestMapping(AuthController.AUTHCONTROLLER_PATH)
public interface AuthController {
    String AUTHCONTROLLER_PATH = AppConstant.BASE_PATH + "/auth/";
    String SIGN_UP = "sign-up";
    String SIGN_IN = "sign-in";
    String CONFIRM_ACCOUNT = "confirm-account";

    String REFRESH_TOKEN = "refresh-token";


    @PostMapping(SIGN_UP)
    ApiResult<?> signUp(@RequestBody RegisterDTO registerDTO);
    @PostMapping(SIGN_IN)
    ApiResult<TokenDTO> signIn(@RequestBody SignInDTO signInDTO);
    @GetMapping(CONFIRM_ACCOUNT)
    ApiResult<?> confirmAccount(@RequestParam Integer userId,
                                @RequestParam String verificationCode);


//    @PostMapping(REFRESH_TOKEN)
//    ApiResult<?> refreshToken(@RequestBody RefreshTokenRequest request);


}
