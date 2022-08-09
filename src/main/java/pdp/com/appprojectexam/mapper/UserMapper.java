package pdp.com.appprojectexam.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import pdp.com.appprojectexam.entity.User;
import pdp.com.appprojectexam.payload.UserInfoDTO;
import pdp.com.appprojectexam.payload.UserUpdateDTO;

@Mapper(componentModel = "spring")
public interface UserMapper {

    void updateEntity(UserUpdateDTO userUpdateDTO, @MappingTarget User user);

    UserInfoDTO mapEntityToInfoDTO(User user);
}
