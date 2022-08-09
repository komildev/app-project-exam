package pdp.com.appprojectexam.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pdp.com.appprojectexam.exception.ApiResult;
import pdp.com.appprojectexam.payload.ProductAddDTO;
import pdp.com.appprojectexam.payload.ProductInfoDTO;
import pdp.com.appprojectexam.payload.ProductUpdateDTO;
import pdp.com.appprojectexam.utils.AppConstant;

@RequestMapping(ProductController.PRODUCT_CONTROLLER_PATH)
public interface ProductController {
    String PRODUCT_CONTROLLER_PATH = AppConstant.BASE_PATH + "/product/";
    String GET_ALL_PATH = "all";
    String ADD_PRODUCT_PATH = "add";
    String UPDATE_PRODUCT_PATH = "update";
    String DELETE_PRODUCT_PATH = "delete";

    String GET_ONE = "get-one";

    @PreAuthorize(value = "hasAuthority('VIEW_PRODUCT')")
    @GetMapping(GET_ONE + "/{id}")
    ApiResult<ProductInfoDTO> getOne(@PathVariable Integer id);

    @PreAuthorize(value = "hasAuthority('VIEW_PRODUCT')")
    @GetMapping(GET_ALL_PATH)
    ApiResult<?> getAll(@RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "10") int size);

    @PreAuthorize(value = "hasAuthority('ADD_PRODUCT')")
    @PostMapping(ADD_PRODUCT_PATH)
    ApiResult<ProductInfoDTO> add(@RequestBody ProductAddDTO product);

    @PreAuthorize(value = "hasAuthority('EDIT_PRODUCT')")
    @PutMapping(UPDATE_PRODUCT_PATH + "/{id}")
    ApiResult<ProductInfoDTO> update(@RequestBody ProductUpdateDTO product, @PathVariable Integer id);

    @PreAuthorize(value = "hasAuthority('DELETE_PRODUCT')")
    @DeleteMapping(DELETE_PRODUCT_PATH + "/{id}")
    String delete(@PathVariable Integer id);
}
