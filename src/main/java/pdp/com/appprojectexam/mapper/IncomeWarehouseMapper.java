package pdp.com.appprojectexam.mapper;

import org.mapstruct.Mapper;
import pdp.com.appprojectexam.entity.IncomeProduct;
import pdp.com.appprojectexam.payload.IncomeProductAddDTO;

@Mapper(componentModel = "spring")

public interface IncomeWarehouseMapper {
    IncomeProduct mapIncomeProduct(IncomeProductAddDTO productAddDTO);
}
