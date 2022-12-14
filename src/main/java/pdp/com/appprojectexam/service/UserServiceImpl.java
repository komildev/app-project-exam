package pdp.com.appprojectexam.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import pdp.com.appprojectexam.entity.Role;
import pdp.com.appprojectexam.entity.User;
import pdp.com.appprojectexam.exception.ApiResult;
import pdp.com.appprojectexam.exception.RestException;
import pdp.com.appprojectexam.mapper.UserMapper;
import pdp.com.appprojectexam.payload.*;
import pdp.com.appprojectexam.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    private final RoleServiceImpl roleService;

    @Override
    public ApiResult<UserInfoDTO> update(UserUpdateDTO userUpdateDTO, Integer id) {
        checkPhoneNumber(userUpdateDTO.getPhoneNumber(), id);
        User user = getUserByIdOrElseThrow(id);
        userMapper.updateEntity(userUpdateDTO, user);
        userRepository.save(user);
        return returnApiResult(user, true, "success");

    }

    @Override
    public ApiResult<UserInfoDTO> getOne(Integer id) {
         User user = getUserByIdOrElseThrow(id);
        UserInfoDTO userInfoDTO = entityToInfoDTO(user);
        return ApiResult.successResponse(userInfoDTO);
    }

    @Override
    public ApiResult<?> getAll(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<User> userPage = userRepository.findAll(pageable);

        List<User> categoryList = userPage.getContent();

        List<UserInfoDTO> userInfoDTOS = categoryList
                .stream()
                .map(this::entityToInfoDTO)
                .collect(Collectors.toList());

        Page<UserInfoDTO> myPage = new PageImpl<>(
                userInfoDTOS,
                userPage.getPageable(),
                userPage.getTotalElements()
        );

        return ApiResult.successResponse(myPage);
    }

    @Override
    public ApiResult<UserInfoDTO> roleUser(UserUpdateDTO userUpdateDTO, Integer userId) {
        User user = getUserByIdOrElseThrow(userId);
        Role role = roleService.getByIdOrElseThrow(userUpdateDTO.getRoleId());
        user.setRole(role);
        userRepository.save(user);
        return returnApiResult(user, true, "Successfully add role");

    }

    @Override
    public String delete(Integer id) {
        User user = getUserByIdOrElseThrow(id);
        user.setEnabled(false);
        userRepository.delete(user);
        return "Successfully deleted";
    }

    private ApiResult<UserInfoDTO> returnApiResult(User user, boolean success, String msg) {
        UserInfoDTO userInfoDTO = entityToInfoDTO(user);
        return new ApiResult<>(userInfoDTO, success, msg);
    }

    private User getUserByIdOrElseThrow(Integer userId) {
        return userRepository.findById(userId).orElseThrow(() -> RestException.notFound("User"));
    }

    private UserInfoDTO entityToInfoDTO(User user) {
        return new UserInfoDTO(
                user.getFirstName(),
                user.getLastName(),
                user.getPhoneNumber(),
                user.getEmail(),
                user.getRole().getId()
                );
    }

    private void checkPhoneNumber(String phoneNumber, Integer id) {
        boolean exist = userRepository.existsByPhoneNumberAndIdAndStatusTrueAndEnabledTrue(phoneNumber, id);
        if (exist) throw RestException.alreadyExist("User");
    }

}
