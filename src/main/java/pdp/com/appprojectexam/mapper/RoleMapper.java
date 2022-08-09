package pdp.com.appprojectexam.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import pdp.com.appprojectexam.entity.Measurement;
import pdp.com.appprojectexam.entity.Role;
import pdp.com.appprojectexam.payload.*;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    void updateEntity(RoleUpdateDTO roleUpdateDTO, @MappingTarget Role role);

    RoleInfoDTO mapEntityToInfoDTO(Role role);

    Role mapAddDTOToEntity(RoleAddDTO roleAddDTO);

}
