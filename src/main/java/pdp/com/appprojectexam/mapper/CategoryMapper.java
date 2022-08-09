package pdp.com.appprojectexam.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import pdp.com.appprojectexam.entity.Category;
import pdp.com.appprojectexam.entity.IncomeProduct;
import pdp.com.appprojectexam.entity.IncomeWarehouse;
import pdp.com.appprojectexam.payload.*;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    void updateEntity(CategoryUpdateDTO categoryUpdateDTO, @MappingTarget Category category);

    CategoryInfoDTO mapEntityToInfoDTO(Category category);

    Category status(Category category);

    Category mapAddDTOToEntity(CategoryAddDTO categoryAddDTO);

    Category mapAddDTOToProductAndCategory(CategoryAddWithProductDTO categoryAddWithProductDTO);

}
