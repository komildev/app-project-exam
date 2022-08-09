package pdp.com.appprojectexam.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import pdp.com.appprojectexam.entity.Category;
import pdp.com.appprojectexam.entity.Product;
import pdp.com.appprojectexam.payload.*;

@Mapper(componentModel = "spring")

public interface ProductMapper {

    void updateEntity(ProductUpdateDTO productUpdateDTO, @MappingTarget Product product);

    Product mapAddDTOToEntity(ProductAddDTO productAddDTO);

    ProductInfoDTO mapEntityToInfoDTO(Product product);


}
