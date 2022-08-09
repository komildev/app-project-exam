package pdp.com.appprojectexam.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pdp.com.appprojectexam.exception.ApiResult;
import pdp.com.appprojectexam.payload.*;
import pdp.com.appprojectexam.utils.AppConstant;

import javax.validation.Valid;

@RequestMapping(CategoryController.CATEGORY_CONTROLLER_PATH)
public interface CategoryController {
    String CATEGORY_CONTROLLER_PATH = AppConstant.BASE_PATH + "/category/";
    String GET_ALL_PATH = "all";
    String ADD_CATEGORY_PATH = "add";
    String UPDATE_CATEGORY_PATH = "update";
    String DELETE_CATEGORY_PATH = "delete";
    String GET_ONE = "get-one";
    String ONE_TIME_SAVE_CATEGORY_AND_PRODUCT = "addCategoryProduct";
    String UPDATE_CATEGORY_PRODUCT = "updateCategoryProduct";

    @PreAuthorize(value = "hasAuthority('VIEW_CATEGORY')")
    @GetMapping(GET_ONE+ "/{id}")
    ApiResult<CategoryInfoDTO> getOne(@PathVariable Integer id);

    @PreAuthorize(value = "hasAuthority('VIEW_CATEGORY')")
    @GetMapping(GET_ALL_PATH)
    ApiResult<?> getAll(@RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "10") int size);
    @PreAuthorize(value = "hasAuthority('ADD_CATEGORY')")
    @PostMapping(ADD_CATEGORY_PATH)
    ApiResult<CategoryInfoDTO> add(@RequestBody @Valid CategoryAddDTO categoryAddDTO);

    @PreAuthorize(value = "hasAuthority('EDIT_CATEGORY')")
    @PutMapping(UPDATE_CATEGORY_PATH + "/{id}")
    ApiResult<CategoryInfoDTO> update(@RequestBody CategoryUpdateDTO categoryUpdateDTO,
                                      @PathVariable Integer id);
    @PreAuthorize(value = "hasAuthority('DELETE_CATEGORY')")
    @DeleteMapping(DELETE_CATEGORY_PATH + "/{id}")
    String delete(@PathVariable Integer id);


    @PostMapping(ONE_TIME_SAVE_CATEGORY_AND_PRODUCT)
    ApiResult<CategoryInfoDTO> addCategoryAndProducts(@RequestBody CategoryAddWithProductDTO categoryAddWithProductDTO);
    @PutMapping(UPDATE_CATEGORY_PRODUCT + "/{id}")
    ApiResult<CategoryInfoDTO> updateCategoryProduct(@RequestBody CategoryEditWithProductDTO categoryUpdateDTO,
                                                     @PathVariable Integer id);
}
