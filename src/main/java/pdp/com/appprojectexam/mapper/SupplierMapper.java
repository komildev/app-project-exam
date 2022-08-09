package pdp.com.appprojectexam.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import pdp.com.appprojectexam.entity.Supplier;
import pdp.com.appprojectexam.payload.SupplierAddDTO;
import pdp.com.appprojectexam.payload.SupplierInfoDTO;
import pdp.com.appprojectexam.payload.SupplierUpdateDTO;

@Mapper(componentModel = "spring")
public interface SupplierMapper {

    void updateEntity(SupplierUpdateDTO supplierUpdateDTO, @MappingTarget Supplier supplier);

    Supplier mapAddDTOToEntity(SupplierAddDTO supplierAddDTO);

    SupplierInfoDTO mapEntityToInfoDTO(Supplier supplier);
}
