package pdp.com.appprojectexam.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import pdp.com.appprojectexam.controller.CategoryController;
import pdp.com.appprojectexam.exception.ApiResult;
import pdp.com.appprojectexam.payload.*;
import pdp.com.appprojectexam.service.CategoryService;

@RestController
@RequiredArgsConstructor
public class CategoryControllerImpl implements CategoryController {
    private final CategoryService categoryService;

    @Override
    public ApiResult<CategoryInfoDTO> getOne(Integer id) {
        return categoryService.getOne(id);
    }

    @Override
    public ApiResult<?> getAll(int page, int size) {
        return categoryService.getAll(page,size);
    }

    @Override
    public ApiResult<CategoryInfoDTO> add(CategoryAddDTO categoryAddDTO) {
        return categoryService.add(categoryAddDTO);
    }

    @Override
    public ApiResult<CategoryInfoDTO> update(CategoryUpdateDTO categoryUpdateDTO, Integer id) {
        return categoryService.update(categoryUpdateDTO,id);
    }

    @Override
    public String delete(Integer id) {
        return categoryService.delete(id);
    }

    @Override
    public ApiResult<CategoryInfoDTO> addCategoryAndProducts(CategoryAddWithProductDTO categoryAddWithProductDTO) {
        return categoryService.addCategoryAndProducts(categoryAddWithProductDTO);
    }

    @Override
    public ApiResult<CategoryInfoDTO> updateCategoryProduct(CategoryEditWithProductDTO categoryUpdateDTO, Integer id) {
        return categoryService.updateCategoryProduct(categoryUpdateDTO,id);
    }


}
