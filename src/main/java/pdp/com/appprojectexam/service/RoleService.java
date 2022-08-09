package pdp.com.appprojectexam.service;

import pdp.com.appprojectexam.enums.PermissionEnum;
import pdp.com.appprojectexam.exception.ApiResult;
import pdp.com.appprojectexam.payload.RoleAddDTO;
import pdp.com.appprojectexam.payload.RoleInfoDTO;
import pdp.com.appprojectexam.payload.RoleUpdateDTO;

import java.util.List;
import java.util.Set;

public interface RoleService {

    ApiResult<?> getAll(int page, int size);

    ApiResult<RoleInfoDTO> add(RoleAddDTO roleAddDTO);

    ApiResult<RoleInfoDTO> update(RoleUpdateDTO roleUpdateDTO, Integer id);

    String delete(Integer id);

    ApiResult<RoleInfoDTO> deletePerRole(List<PermissionEnum> permissionEnums, Integer id);

    ApiResult<RoleInfoDTO> addPerRole(Set<PermissionEnum> permissionEnums, Integer id);

    ApiResult<RoleInfoDTO> getOne(Integer id);
}
