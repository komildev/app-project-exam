package pdp.com.appprojectexam.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pdp.com.appprojectexam.controller.RoleController;
import pdp.com.appprojectexam.enums.PermissionEnum;
import pdp.com.appprojectexam.exception.ApiResult;
import pdp.com.appprojectexam.payload.RoleAddDTO;
import pdp.com.appprojectexam.payload.RoleInfoDTO;
import pdp.com.appprojectexam.payload.RoleUpdateDTO;
import pdp.com.appprojectexam.service.RoleService;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RoleControllerImpl implements RoleController {

    private final RoleService roleService;

    @Override
    public ApiResult<RoleInfoDTO> getOne(Integer id) {
        return roleService.getOne(id);
    }

    @Override
    public ApiResult<?> getAll(int page, int size) {
        return roleService.getAll(page, size);
    }

    @Override
    public ApiResult<RoleInfoDTO> add(RoleAddDTO roleAddDTO) {
        return roleService.add(roleAddDTO);
    }

    @Override
    public String delete(Integer id) {
        return roleService.delete(id);
    }

    @Override
    public ApiResult<RoleInfoDTO> update(RoleUpdateDTO roleUpdateDTO, Integer id) {
        return roleService.update(roleUpdateDTO,id);
    }

    @Override
    public ApiResult<RoleInfoDTO> deletePerRole(List<PermissionEnum> permissionEnums, Integer id) {
        return roleService.deletePerRole(permissionEnums,id);
    }

    @Override
    public ApiResult<RoleInfoDTO> addPerRole(Set<PermissionEnum> permissionEnums, Integer id) {
        return roleService.addPerRole(permissionEnums,id);
    }
}
