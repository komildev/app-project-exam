package pdp.com.appprojectexam.service;

import pdp.com.appprojectexam.exception.ApiResult;
import pdp.com.appprojectexam.payload.*;

public interface CategoryService {
    ApiResult<CategoryInfoDTO> getOne(Integer id);

    ApiResult<?> getAll(int page, int size);

    ApiResult<CategoryInfoDTO> add(CategoryAddDTO categoryAddDTO);

    ApiResult<CategoryInfoDTO> update(CategoryUpdateDTO categoryUpdateDTO, Integer id);

    String delete(Integer id);

    ApiResult<CategoryInfoDTO> addCategoryAndProducts(CategoryAddWithProductDTO categoryAddWithProductDTO);

    ApiResult<CategoryInfoDTO> updateCategoryProduct(CategoryEditWithProductDTO categoryUpdateDTO, Integer id);
}
