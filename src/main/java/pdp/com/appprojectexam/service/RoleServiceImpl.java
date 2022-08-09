package pdp.com.appprojectexam.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pdp.com.appprojectexam.entity.Role;
import pdp.com.appprojectexam.enums.PermissionEnum;
import pdp.com.appprojectexam.exception.ApiResult;
import pdp.com.appprojectexam.exception.RestException;
import pdp.com.appprojectexam.payload.*;
import pdp.com.appprojectexam.repository.RoleRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public ApiResult<RoleInfoDTO> getOne(Integer id) {
        Role role = getByIdOrElseThrow(id);
        RoleInfoDTO roleInfoDTO = entityToInfoDTO(role);
        return ApiResult.successResponse(roleInfoDTO);
    }

    @Override
    public ApiResult<?> getAll(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<Role> rolePage = roleRepository.findAll(pageable);

        List<Role> roleList = rolePage.getContent();

        List<RoleInfoDTO> roleDTOList = roleList
                .stream()
                .map(this::entityToInfoDTO)
                .collect(Collectors.toList());

        Page<RoleInfoDTO> myPage = new PageImpl<>(
                roleDTOList,
                rolePage.getPageable(),
                rolePage.getTotalElements()
        );
        return ApiResult.successResponse(myPage);
    }


    @Override
    public ApiResult<RoleInfoDTO> add(RoleAddDTO roleAddDTO) {
        checkName(roleAddDTO.getName());
        Role role = new Role(
                roleAddDTO.getName(),
                roleAddDTO.getDescription(),
                roleAddDTO.getPermissionEnums()
        );
        roleRepository.save(role);
        return returnApiResult(role, true, "Successfully created");
    }

    @Override
    public ApiResult<RoleInfoDTO> update(RoleUpdateDTO roleUpdateDTO, Integer id) {
        checkName(roleUpdateDTO.getName(), id);
        Role role = getByIdOrElseThrow(id);
        Set<PermissionEnum> permissionEnums = role.getPermissions();
        role.setName(roleUpdateDTO.getName());
        role.setDescription(roleUpdateDTO.getDescription());
        permissionEnums.addAll(roleUpdateDTO.getPermissionEnums());
        role.setPermissions(permissionEnums);
        roleRepository.save(role);
        return returnApiResult(role, true, "success");

    }

    @Override
    public String delete(Integer id) {
        Role role = getByIdOrElseThrow(id);
        roleRepository.delete(role);
        return "Successfully deleted role";

    }

    @Override
    public ApiResult<RoleInfoDTO> deletePerRole(List<PermissionEnum> permissionEnums, Integer id) {
        Role role = getByIdOrElseThrow(id);
        Set<PermissionEnum> permissions = role.getPermissions();
        permissions.removeAll(permissionEnums);
        role.setPermissions(permissions);
        roleRepository.save(role);
        return ApiResult.successResponse(entityToInfoDTO(role));
    }

    @Override
    public ApiResult<RoleInfoDTO> addPerRole(Set<PermissionEnum> permissionEnums, Integer id) {
        Role role = getByIdOrElseThrow(id);
        Set<PermissionEnum> permissionEnumSet = new HashSet<PermissionEnum>();
        for (PermissionEnum permission : role.getPermissions()) {
            permissionEnumSet.add(permission);
        }
        role.setPermissions(permissionEnumSet);
        roleRepository.save(role);
        return ApiResult.successResponse(entityToInfoDTO(role));
    }


    private RoleInfoDTO entityToInfoDTO(Role role) {
        return new RoleInfoDTO(
                role.getName(),
                role.getDescription(),
                role.getPermissions()
        );
    }

    private void checkName(String name) {
        boolean exists = roleRepository.existsByNameAndDeletedFalse(name);
        if (exists) throw RestException.alreadyExist("Role");
    }

    private void checkName(String name, Integer id) {
        boolean exists = roleRepository.existsByNameAndIdAndDeletedFalse(name, id);
        if (exists) throw RestException.alreadyExist("Role");
    }

    public Role getByIdOrElseThrow(Integer roleId) {
        return roleRepository.findById(roleId).orElseThrow(()
                -> RestException.notFound("Role"));
    }

    private ApiResult<RoleInfoDTO> returnApiResult(Role role, boolean success, String msg) {
        RoleInfoDTO roleDTO = entityToInfoDTO(role);
        return new ApiResult<>(roleDTO, success, msg);
    }
}
