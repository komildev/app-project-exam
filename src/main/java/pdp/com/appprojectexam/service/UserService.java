package pdp.com.appprojectexam.service;

import pdp.com.appprojectexam.exception.ApiResult;
import pdp.com.appprojectexam.payload.ManageRoleUserDTO;
import pdp.com.appprojectexam.payload.RoleInfoDTO;
import pdp.com.appprojectexam.payload.UserInfoDTO;
import pdp.com.appprojectexam.payload.UserUpdateDTO;

public interface UserService {

    ApiResult<UserInfoDTO> roleUser(UserUpdateDTO userUpdateDTO, Integer userId);

    String delete(Integer id);

    ApiResult<UserInfoDTO> update(UserUpdateDTO userUpdateDTO, Integer id);

    ApiResult<?> getOne(Integer id);

    ApiResult<?> getAll(int page, int size);
}
