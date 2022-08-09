package pdp.com.appprojectexam.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import pdp.com.appprojectexam.controller.UserController;
import pdp.com.appprojectexam.exception.ApiResult;
import pdp.com.appprojectexam.payload.UserInfoDTO;
import pdp.com.appprojectexam.payload.UserUpdateDTO;
import pdp.com.appprojectexam.service.UserService;

@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserService userService;

    @Override
    public ApiResult<?> getOne(Integer id) {
        return userService.getOne(id);
    }

    @Override
    public ApiResult<?> getAll(int page, int size) {
        return userService.getAll(page,size);
    }

    @Override
    public ApiResult<UserInfoDTO> roleUser(UserUpdateDTO userUpdateDTO, Integer id) {
        return userService.roleUser(userUpdateDTO,id);
    }

    @Override
    public ApiResult<UserInfoDTO> update(UserUpdateDTO userUpdateDTO, Integer id) {
        return userService.update(userUpdateDTO,id);
    }

    @Override
    public String delete(Integer id) {
        return userService.delete(id);
    }
}
