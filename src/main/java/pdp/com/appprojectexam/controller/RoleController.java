package pdp.com.appprojectexam.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pdp.com.appprojectexam.enums.PermissionEnum;
import pdp.com.appprojectexam.exception.ApiResult;
import pdp.com.appprojectexam.payload.RoleAddDTO;
import pdp.com.appprojectexam.payload.RoleInfoDTO;
import pdp.com.appprojectexam.payload.RoleUpdateDTO;
import pdp.com.appprojectexam.utils.AppConstant;

import java.util.List;
import java.util.Set;

@RequestMapping(RoleController.ROLE_PATH)
public interface RoleController {
    String ROLE_PATH = AppConstant.BASE_PATH + "/role/";
    String GET_ONE = "get-one";
    String ALL = "all";
    String ADD = "add";
    String UPDATE = "update";
    String DELETE = "delete";
    String ADD_PERMISSIONS_ROLE = "addPermissionsRole";
    String DELETE_PERMISSIONS_ROLE = "deletePermissionsRole";


    @PreAuthorize(value = "hasAuthority('VIEW_ROLE')")
    @GetMapping(GET_ONE+"/{id}")
    ApiResult<RoleInfoDTO> getOne(@PathVariable Integer id);

    @PreAuthorize(value = "hasAuthority('VIEW_ROLE')")
    @GetMapping(ALL)
    ApiResult<?> getAll(@RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "10") int size);

    @PreAuthorize(value = "hasAuthority('ADD_ROLE')")
    @PostMapping(ADD)
    ApiResult<RoleInfoDTO> add(@RequestBody RoleAddDTO roleAddDTO);

    @PreAuthorize(value = "hasAuthority('DELETE_ROLE')")
    @DeleteMapping(DELETE + "/{id}")
    String delete(@PathVariable Integer id);

    @PreAuthorize(value = "hasAuthority('EDIT_ROLE')")
    @PutMapping(UPDATE + "/{id}")
    ApiResult<RoleInfoDTO> update(@RequestBody RoleUpdateDTO roleUpdateDTO, @PathVariable Integer id);

    @PreAuthorize(value = "hasAuthority('DELETE_PERMISSIONS_ROLE')")
    @DeleteMapping(DELETE_PERMISSIONS_ROLE+"/{id}")
    ApiResult<RoleInfoDTO> deletePerRole(@RequestBody List<PermissionEnum> permissionEnums, @PathVariable Integer id);

    @PreAuthorize(value = "hasAuthority('ADD_PERMISSIONS_ROLE')")
    @PostMapping(ADD_PERMISSIONS_ROLE + "/{id}")
    ApiResult<RoleInfoDTO> addPerRole(@RequestBody Set<PermissionEnum> permissionEnums, @PathVariable Integer id);


}
