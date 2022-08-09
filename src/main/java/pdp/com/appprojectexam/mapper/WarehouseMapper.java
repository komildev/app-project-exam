package pdp.com.appprojectexam.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import pdp.com.appprojectexam.entity.WareHouse;
import pdp.com.appprojectexam.payload.WareHouseAddDTO;
import pdp.com.appprojectexam.payload.WareHouseInfoDTO;

@Mapper(componentModel = "spring")
public interface WarehouseMapper {

    void updateEntity(WareHouseAddDTO wareHouseAddDTO, @MappingTarget WareHouse wareHouse);

    WareHouse mapAddDTOToEntity(WareHouseAddDTO wareHouseAddDTO);

    WareHouseInfoDTO mapEntityToInfoDTO(WareHouse wareHouse);
}
